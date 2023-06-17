package com.example.healthapp.jogConstructor.data.mappers.realmMappers

import com.example.healthapp.core.data.IRealmMapper
import com.example.healthapp.jogConstructor.data.entities.JogRealm
import com.example.healthapp.jogConstructor.domain.entities.Jog
import io.realm.RealmList
import javax.inject.Inject

class JogRealmMapper @Inject constructor(
    private val dataPointRealmMapper: DataPointRealmMapper
) : IRealmMapper<Jog, JogRealm> {

    override fun fromRealm(items: List<JogRealm>): List<Jog> = items.map {
        return@map Jog(
            id = it.uid!!,
            name = it.name!!,
            points = dataPointRealmMapper.fromRealm(it.dataPoints),
            distance = it.distance!!
        )
    }

    override fun toRealm(items: List<Jog>): RealmList<JogRealm> {
        val realmList: RealmList<JogRealm> = RealmList()

        items.forEach {
            realmList.add(
                JogRealm(
                    uid = it.id,
                    name = it.name,
                    dataPoints = dataPointRealmMapper.toRealm(it.points)
                )
            )
        }

        return realmList
    }
}