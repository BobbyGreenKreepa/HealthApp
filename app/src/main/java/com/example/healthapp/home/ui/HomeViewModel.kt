package com.example.healthapp.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.trains.GetTrainsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrainsCase: GetTrainsUseCase
) : ViewModel() {

    fun a(){
        viewModelScope.launch {
            val value = getTrainsCase.invoke()
            value.collect {it -> Log.e("123123", it.size.toString())}
        }
    }
}