package com.example.healthapp.trainConstructor.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.trainConstructor.domain.entities.Exercise
import com.example.healthapp.trainConstructor.domain.entities.Train
import com.example.healthapp.trainConstructor.domain.useCasesImpl.AddTrainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TrainConstructorViewModel @Inject constructor(
    private val addTrainUseCase: AddTrainUseCase,
) : ViewModel() {

    private val _trainUid: Flow<String> = flowOf(UUID.randomUUID().toString())

    val trainName: MutableStateFlow<String> = MutableStateFlow("")
    val trainDate: MutableStateFlow<String> = MutableStateFlow("")
    val exercises: MutableStateFlow<MutableList<Exercise>> = MutableStateFlow(mutableListOf())

    val viewState: Flow<Train> = combine(
        _trainUid,
        trainName,
        trainDate,
        exercises,
        ::mergeSources
    )

    fun addTrain() {
        viewModelScope.launch {
            addTrainUseCase.invoke(viewState.single())
        }
    }

    private fun mergeSources(uid: String, name: String, date: String, exercises: List<Exercise>): Train {
        return Train(
            uid = uid,
            name = name,
            dateTime = date,
            exercises = exercises
        )

        Log.e("123123", "emited")
    }
}
