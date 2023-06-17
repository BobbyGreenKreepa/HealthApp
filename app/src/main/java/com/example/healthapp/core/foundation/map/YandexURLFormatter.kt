package com.example.healthapp.core.foundation.map

import com.example.healthapp.BuildConfig
import com.example.healthapp.jogConstructor.domain.entities.DataPoint
import javax.inject.Inject

class YandexURLFormatter @Inject constructor() {

    fun format(points: List<DataPoint>) : String {
        val url = StringBuilder(BuildConfig.yandexStaticApiURL)

        points.forEach {
            url.append(it.xPoint).append(',').append(it.yPoint).append(',')
        }

        url.deleteAt(url.length - 1)
        return url.toString()
    }
}