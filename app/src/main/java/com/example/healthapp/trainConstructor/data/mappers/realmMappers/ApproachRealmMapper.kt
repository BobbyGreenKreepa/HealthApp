package com.example.healthapp.trainConstructor.data.mappers.realmMappers

import com.example.healthapp.core.data.IRealmMapper
import com.example.healthapp.trainConstructor.data.entities.ApproachRealm
import com.example.healthapp.trainConstructor.domain.entities.Approach
import io.realm.RealmList
import javax.inject.Inject

class ApproachRealmMapper @Inject constructor() : IRealmMapper<Approach, ApproachRealm> {

    override fun fromRealm(items: List<ApproachRealm>): List<Approach> = items.map {
           return@map Approach(
            uid = it.uid!!,
            exerciseId = it.exerciseId!!,
            index = it.index!!,
            complexity = it.complexity!!,
            repeats =  it.repeats!!,
            duration = it.duration!!
        )
    }

    override fun toRealm(items: List<Approach>): RealmList<ApproachRealm> {
        val realmList: RealmList<ApproachRealm> = RealmList()

        items.forEach {realmList.add(ApproachRealm(
            uid = it.uid,
            exerciseId = it.exerciseId,
            index = it.index,
            complexity = it.complexity,
            repeats = it.repeats,
            duration = it.duration
        ))}

        return realmList;
    }
}