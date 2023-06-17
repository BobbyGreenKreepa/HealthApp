package com.example.healthapp.jogConstructor.ui.mappers

import com.example.healthapp.core.mapper.ILayerMapper
import com.example.healthapp.jogConstructor.domain.entities.DataPoint
import com.example.healthapp.jogConstructor.domain.entities.Jog
import com.example.healthapp.jogConstructor.ui.entities.UiDataPoint
import com.example.healthapp.jogConstructor.ui.entities.UiJog
import com.yandex.mapkit.geometry.Point
import javax.inject.Inject

class UiJogLayerMapper @Inject constructor()  : ILayerMapper<Jog, UiJog> {

    override fun toDomain(value: UiJog): Jog {
        return Jog(
            id = value.uid,
            name = value.name,
            points = value.uiDataPoints.map {
                return@map DataPoint(
                    data = it.data,
                    xPoint = it.point.latitude,
                    yPoint = it.point.longitude
                )
            },
            distance = value.distance
        )
    }

    override fun fromDomain(value: Jog): UiJog {
        return UiJog(
            uid = value.id,
            name = value.name,
            uiDataPoints = value.points.map {
                return@map UiDataPoint(
                    point = Point(it.xPoint, it.yPoint),
                    data = it.data
                )
            },
            distance = value.distance
        )
    }
}