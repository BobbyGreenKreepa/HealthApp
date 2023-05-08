package com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.exercises

import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import com.example.healthapp.trains.trainConstructor.domain.repos.IExerciseRepository
import javax.inject.Inject

class AddExerciseCase @Inject constructor(
    private val exercisesRepository: IExerciseRepository
) : suspend (Exercise) -> Unit {

    override suspend fun invoke(exercise: Exercise) {
        exercisesRepository.addExercise(exercise)
    }
}