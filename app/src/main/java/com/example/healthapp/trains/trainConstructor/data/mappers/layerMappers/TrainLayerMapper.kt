package com.example.healthapp.trains.trainConstructor.data.mappers.layerMappers

import com.example.healthapp.core.mapper.ILayerMapper
import com.example.healthapp.trains.trainConstructor.data.entities.TrainRealm
import com.example.healthapp.trains.trainConstructor.data.mappers.realmMappers.ExerciseFromRealm
import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import javax.inject.Inject

class TrainLayerMapper @Inject constructor(
    private val mapper: ExerciseFromRealm
): ILayerMapper<Train, TrainRealm> {

    override fun toDomain(value: TrainRealm): Train = Train(
        uid = value.id!!,
        name = value.name!!,
        dateTime = value.dateTime!!,
        exercises = mapper.fromRealm(value.exercises!!)
    )

    override fun fromDomain(value: Train): TrainRealm = TrainRealm(
        id = value.uid,
        name = value.name,
        dateTime = value.dateTime,
        exercises = mapper.toRealm(value.exercises))
}