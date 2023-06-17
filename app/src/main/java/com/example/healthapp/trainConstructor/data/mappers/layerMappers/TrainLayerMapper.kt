package com.example.healthapp.trainConstructor.data.mappers.layerMappers

import com.example.healthapp.core.mapper.ILayerMapper
import com.example.healthapp.trainConstructor.data.entities.TrainRealm
import com.example.healthapp.trainConstructor.data.mappers.realmMappers.ExerciseRealmMapper
import com.example.healthapp.trainConstructor.domain.entities.Train
import javax.inject.Inject

class TrainLayerMapper @Inject constructor(
    private val mapper: ExerciseRealmMapper
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
