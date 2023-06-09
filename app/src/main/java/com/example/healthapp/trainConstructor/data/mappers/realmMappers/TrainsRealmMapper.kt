package com.example.healthapp.trainConstructor.data.mappers.realmMappers

import com.example.healthapp.core.data.IRealmMapper
import com.example.healthapp.trainConstructor.data.entities.TrainRealm
import com.example.healthapp.trainConstructor.domain.entities.Train
import io.realm.RealmList
import javax.inject.Inject

class TrainsRealmMapper @Inject constructor(
    private val mapper: ExerciseRealmMapper
) : IRealmMapper<Train, TrainRealm> {

    override fun fromRealm(items: List<TrainRealm>): List<Train> = items.map {
        return@map Train(
            uid = it.id!!,
            name = it.name!!,
            dateTime = it.dateTime!!,
            exercises = mapper.fromRealm(it.exercises!!)
        )
    }

    override fun toRealm(items: List<Train>): RealmList<TrainRealm> {
        val realmList: RealmList<TrainRealm> = RealmList()

        items.forEach { realmList.add(TrainRealm(
            id = it.uid,
            name = it.name,
            dateTime = it.dateTime,
            exercises = mapper.toRealm(it.exercises)))
        }

        return realmList
    }
}