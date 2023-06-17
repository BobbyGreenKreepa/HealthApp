package com.example.healthapp.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.core.foundation.map.YandexURLFormatter
import com.example.healthapp.home.domain.useCases.GetJogsUseCase
import com.example.healthapp.home.domain.useCases.GetTrainsUseCase
import com.example.healthapp.jogConstructor.domain.entities.Jog
import com.example.healthapp.trainConstructor.domain.entities.Train
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrainsCase: GetTrainsUseCase,
    private val getJogsUseCase: GetJogsUseCase,
    val yandexURLFormatter: YandexURLFormatter
) : ViewModel() {

    val jogs: Flow<List<Jog>> = getJogsUseCase.invoke()

    val trains: Flow<List<Train>> = getTrainsCase.invoke()
    fun a(){
        viewModelScope.launch {
            val value = getTrainsCase.invoke()
            value.collect {it -> Log.e("123123", it.size.toString())}
        }
    }
}