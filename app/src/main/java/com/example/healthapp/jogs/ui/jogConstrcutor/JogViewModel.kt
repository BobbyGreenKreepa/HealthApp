package com.example.healthapp.jogs.ui.jogConstrcutor

import androidx.lifecycle.ViewModel
import com.yandex.mapkit.geometry.Geo
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class JogViewModel @Inject constructor() :  ViewModel() {

    private val polylinePoints = mutableListOf<Point>()

    private val _polyline: MutableStateFlow<Polyline> = MutableStateFlow(Polyline(polylinePoints))
    val polyline: StateFlow<Polyline> get() = _polyline

    val lastAddedPoint: Point? get() = polylinePoints.lastOrNull()

    private var intDistance = 0
    private val _distance: MutableStateFlow<String> = MutableStateFlow("")
    val distance: StateFlow<String> get() = _distance

    fun addPoint(point: Point) {
        polylinePoints.add(point)
        _polyline.value = Polyline(polylinePoints)

        if (polylinePoints.size > 1) {
            intDistance += Geo.distance(polylinePoints[polylinePoints.size-2], polylinePoints[polylinePoints.size-1]).toInt()
            _distance.value = intDistance.toString()
        }
    }

    fun popPoint() {
        if (polylinePoints.isEmpty()) return
        polylinePoints.removeLast()
        _polyline.value = Polyline(polylinePoints)
    }
}