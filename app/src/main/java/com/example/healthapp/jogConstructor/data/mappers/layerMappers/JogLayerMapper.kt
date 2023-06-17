package com.example.healthapp.jogConstructor.data.mappers.layerMappers

import com.example.healthapp.core.mapper.ILayerMapper
import com.example.healthapp.jogConstructor.data.entities.JogRealm
import com.example.healthapp.jogConstructor.data.mappers.realmMappers.DataPointRealmMapper
import com.example.healthapp.jogConstructor.domain.entities.Jog
import javax.inject.Inject

class JogLayerMapper @Inject constructor(
    private val dataPointRealmMapper: DataPointRealmMapper
) : ILayerMapper<Jog, JogRealm> {

    override fun toDomain(value: JogRealm): Jog {
        return Jog(
            id = value.uid!!,
            name = value.name!!,
            points = dataPointRealmMapper.fromRealm(value.dataPoints),
            distance = value.distance!!
        )
    }

    override fun fromDomain(value: Jog): JogRealm {
        return JogRealm(
            uid = value.id,
            name = value.name,
            dataPoints = dataPointRealmMapper.toRealm(value.points),
            distance = value.distance
        )
    }

}