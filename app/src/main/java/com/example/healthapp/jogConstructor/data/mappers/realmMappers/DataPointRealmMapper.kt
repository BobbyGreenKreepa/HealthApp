package com.example.healthapp.jogConstructor.data.mappers.realmMappers

import com.example.healthapp.core.data.IRealmMapper
import com.example.healthapp.jogConstructor.data.entities.DataPointRealm
import com.example.healthapp.jogConstructor.domain.entities.DataPoint
import io.realm.RealmList
import javax.inject.Inject

class DataPointRealmMapper @Inject constructor() : IRealmMapper<DataPoint, DataPointRealm> {

    override fun fromRealm(items: List<DataPointRealm>): List<DataPoint> = items.map {
        return@map DataPoint(
            data = it.data!!,
            xPoint = it.xPoint!!,
            yPoint = it.yPoint!!
        )
    }

    override fun toRealm(items: List<DataPoint>): RealmList<DataPointRealm> {
        val realmList: RealmList<DataPointRealm> = RealmList()

        items.forEach {
            realmList.add(
                DataPointRealm(
                    data = it.data,
                    xPoint = it.xPoint,
                    yPoint =  it.yPoint
                )
            )
        }

        return realmList
    }
}