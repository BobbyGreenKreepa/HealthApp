package com.example.healthapp.trains.trainConstructor.data.mappers.realmMappers

import com.example.healthapp.core.data.IRealmMapper
import com.example.healthapp.trains.trainConstructor.data.entities.ExerciseRealm
import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import io.realm.RealmList
import javax.inject.Inject

class ExerciseFromRealm @Inject constructor(
    private val realmMapper: ApproachFromRealm
) : IRealmMapper<Exercise, ExerciseRealm> {

    override fun fromRealm(items: List<ExerciseRealm>): List<Exercise> = items.map {
        return@map Exercise(
            uid = it.uid!!,
            trainId = it.trainId!!,
            name = it.name!!,
            duration = it.duration!!,
            approaches = realmMapper.fromRealm(it.approaches!!)
        )
    }

    override fun toRealm(items: List<Exercise>): RealmList<ExerciseRealm> {
        val realmList: RealmList<ExerciseRealm> = RealmList()

        items.forEach { realmList.add(ExerciseRealm(
            uid = it.uid,
            trainId = it.trainId,
            name = it.name,
            duration = it.duration,
            approaches = realmMapper.toRealm(it.approaches)))
        }

        return realmList
    }
}