package com.example.healthapp.trains.trainConstructor.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.trains.trainConstructor.domain.entities.Approach
import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.AddTrainUseCase
import com.example.healthapp.trains.trainConstructor.ui.exercises.ExerciseListItem
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

    private val _exercises: MutableStateFlow<MutableList<ExerciseListItem>> = MutableStateFlow(mutableListOf())
    val exercises: StateFlow<List<ExerciseListItem>> get() = _exercises

    fun addTrain() {

    }

    fun addExercise() {
        val list: MutableList<ExerciseListItem>  = (_exercises.value + ExerciseListItem(Exercise("1", UUID.randomUUID().toString(), "123", 123, listOf(Approach("1", "2", 1, "123", 1, 2), Approach("1", "2", 1, "123", 1, 2))), false)) as MutableList<ExerciseListItem>
        _exercises.value = list
        Log.e("123123", exercises.value.size.toString())
    }
}
