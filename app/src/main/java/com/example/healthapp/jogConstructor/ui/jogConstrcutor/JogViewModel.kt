package com.example.healthapp.jogConstructor.ui.jogConstrcutor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.core.foundation.coroutines.*
import com.example.healthapp.jogConstructor.domain.useCases.AddJogUseCase
import com.example.healthapp.jogConstructor.ui.entities.UiDataPoint
import com.example.healthapp.jogConstructor.ui.entities.UiJog
import com.example.healthapp.jogConstructor.ui.mappers.UiJogLayerMapper
import com.yandex.mapkit.geometry.Geo
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JogViewModel @Inject constructor(
    private val jogMapper: UiJogLayerMapper,
    private val addJogUseCase: AddJogUseCase,
) :  ViewModel() {

    private val collapseInfoTimer: TimerHandler = TimerHandler(viewModelScope)
    val collapseInfoAlarm: SharedFlow<Unit> get() = collapseInfoTimer.alarm

    private val _polylinePoints: MutableStateFlow<List<UiDataPoint>> = MutableStateFlow(emptyList())
    val polylinePoints: StateFlow<List<UiDataPoint>> get() = _polylinePoints

    private val _polyline: MutableStateFlow<Polyline> = MutableStateFlow(createFromUiDataPoint(_polylinePoints.value))
    val polyline: StateFlow<Polyline> get() = _polyline

    private val _phantomPolyline: MutableStateFlow<Polyline> = MutableStateFlow(Polyline(emptyList()))
    val phantomPolyline: StateFlow<Polyline> get() = _phantomPolyline

    private val lastAddedPoint: UiDataPoint? get() = _polylinePoints.value.lastOrNull()

    val jogName: MutableStateFlow<String> = MutableStateFlow("")

    private var intDistance = 0
    private val _distance: MutableStateFlow<String> = MutableStateFlow("")
    val distance: StateFlow<String> get() = _distance

    fun save() {
        viewModelScope.launch {
            val uiJog = UiJog(
                uid = UUID.randomUUID().toString(),
                name = jogName.value,
                uiDataPoints = _polylinePoints.value,
                distance = intDistance
            )

            addJogUseCase.invoke(jogMapper.toDomain(uiJog))
        }
    }

    fun resetCollapseTimer() {
        collapseInfoTimer.reset()
    }

    fun validate() : Boolean {
        return jogName.value.isNotEmpty() && polylinePoints.value.isNotEmpty()
    }

    fun updatePhantomPoint(cameraPoint: Point) {
        if (lastAddedPoint == null) {
            return
        }

        _phantomPolyline.value = createFromUiDataPoint(mutableListOf(lastAddedPoint!!, UiDataPoint(cameraPoint, "")))
    }

    fun addPoint(point: Point) {
        if (lastAddedPoint != null) intDistance += Geo.distance(lastAddedPoint?.point, point).toInt()
        _distance.value = intDistance.toString() + "М."
        _polylinePoints.add(UiDataPoint(point, _distance.value))
        _polyline.value = createFromUiDataPoint(_polylinePoints.value)
    }

    fun popPoint() {
        if (_polylinePoints.isEmpty()) return

        if (_polylinePoints.size > 1) {
            intDistance -= Geo.distance(_polylinePoints.get(_polylinePoints.size-2).point,
                lastAddedPoint?.point).toInt()
            _distance.value = intDistance.toString()
        }

        _polylinePoints.removeLast()
        _polyline.value = createFromUiDataPoint(_polylinePoints.value)
    }

    private fun createFromUiDataPoint(dataPoints: List<UiDataPoint>) : Polyline {
        val points = dataPoints.map {
            return@map it.point
        }

        return Polyline(points)
    }
}