package com.example.healthapp.trains.trainConstructor.ui

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.core.foundation.coroutines.add
import com.example.healthapp.core.foundation.coroutines.clear
import com.example.healthapp.core.foundation.coroutines.remove
import com.example.healthapp.trains.trainConstructor.domain.entities.Approach
import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.trains.AddTrainUseCase
import com.example.healthapp.trains.trainConstructor.ui.train.exercises.ExerciseListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TrainConstructorViewModel @Inject constructor(
    private val addTrainUseCase: AddTrainUseCase,
) : ViewModel() {

    private val _trainUid: String = UUID.randomUUID().toString()
    private var currentExerciseId: String = UUID.randomUUID().toString()

    val trainName: MutableStateFlow<String> = MutableStateFlow("")
    val trainDate: MutableStateFlow<String> = MutableStateFlow("")
    private val _exercises: MutableStateFlow<MutableList<ExerciseListItem>> = MutableStateFlow(mutableListOf())
    val exercises: StateFlow<List<ExerciseListItem>> get() = _exercises

    val currentExerciseName: MutableStateFlow<String> = MutableStateFlow("")
    private val _currentExerciseApproaches: MutableStateFlow<MutableList<Approach>> = MutableStateFlow(mutableListOf())
    val currentExerciseApproaches: Flow<List<Approach>> get() = _currentExerciseApproaches

    val currentApproachComplexity: MutableStateFlow<String> = MutableStateFlow("")
    val currentApproachDuration: MutableStateFlow<String> = MutableStateFlow("")
    val currentApproachRepeats: MutableStateFlow<String> = MutableStateFlow("")

    fun addTrain() {
        viewModelScope.launch {
            addTrainUseCase.invoke(Train(
                uid = _trainUid,
                name = trainName.value,
                dateTime = trainDate.value,
                exercises = exercises.value.map { it.value }
            ))
        }
    }

    fun addExercise() {
        val exercise = Exercise(
            uid = UUID.randomUUID().toString(),
            trainId = _trainUid,
            name = currentExerciseName.value,
            duration = "exerciseDuration",
            approaches = _currentExerciseApproaches.value
        )
        val value = _exercises.value
        value.add(ExerciseListItem(exercise, false))
        _exercises.value = value

        _currentExerciseApproaches.clear()
        currentExerciseId = UUID.randomUUID().toString()
    }

    fun removeExercise(exerciseItem: ExerciseListItem) {
        _exercises.remove(exerciseItem)
    }

    fun validateApproach(onValidate: (Boolean) -> Unit) {
        onValidate(currentApproachComplexity.value.isNotEmpty() && currentApproachDuration.value.isNotEmpty())
    }

    fun validateExercise(onValidate: (Boolean) -> Unit) {
        onValidate(_currentExerciseApproaches.value.isNotEmpty() && currentExerciseName.value.isNotEmpty())
    }

    fun validateTrain(onValidate: (Boolean) -> Unit) {
        onValidate(exercises.value.isNotEmpty() && trainDate.value.isNotEmpty() && trainName.value.isNotEmpty())
    }

    fun addApproach() {
        _currentExerciseApproaches.add(Approach(
            uid = UUID.randomUUID().toString(),
            exerciseId = currentExerciseId,
            index = String.format("${_currentExerciseApproaches.value.size + 1} подход"),
            complexity = currentApproachComplexity.value,
            duration = currentApproachDuration.value,
            repeats = currentApproachRepeats.value
        ))

        currentApproachComplexity.clear()
        currentApproachRepeats.clear()
        currentApproachDuration.clear()
    }

    fun clearNonConsistentExercise() {
        _currentExerciseApproaches.clear()
        currentApproachRepeats.clear()
        currentApproachDuration.clear()
        currentExerciseName.clear()
    }

    fun removeApproach(approach: Approach) {
        _currentExerciseApproaches.remove(approach)
    }

    override fun onCleared() {
        Log.e("ViewModel${this}", "cleared")
        super.onCleared()
    }
}
