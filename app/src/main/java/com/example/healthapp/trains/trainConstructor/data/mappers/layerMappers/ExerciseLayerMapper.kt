package com.example.healthapp.trains.trainConstructor.data.mappers.layerMappers

import com.example.healthapp.core.mapper.ILayerMapper
import com.example.healthapp.trains.trainConstructor.data.entities.ExerciseRealm
import com.example.healthapp.trains.trainConstructor.data.mappers.realmMappers.ApproachFromRealm
import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import javax.inject.Inject

class ExerciseLayerMapper @Inject constructor(
    private val mapper: ApproachFromRealm
) : ILayerMapper<Exercise, ExerciseRealm> {

    override fun toDomain(value: ExerciseRealm): Exercise = Exercise(
        uid = value.uid!!,
        trainId = value.trainId!!,
        name = value.name!!,
        duration = value.duration!!,
        approaches = mapper.fromRealm(value.approaches!!)
    )

    override fun fromDomain(value: Exercise): ExerciseRealm = ExerciseRealm(
        uid = value.uid,
        trainId = value.trainId,
        name = value.name,
        duration = value.duration,
        approaches = mapper.toRealm(value.approaches)
    )
}
