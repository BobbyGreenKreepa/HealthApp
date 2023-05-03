package com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.exercises

import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import com.example.healthapp.trains.trainConstructor.domain.repos.IExerciseRepository
import javax.inject.Inject

class DeleteExerciseCase @Inject constructor(
    private val exercisesRepository: IExerciseRepository
) : suspend (Exercise) -> Unit {

    override suspend fun invoke(exercise: Exercise) {
        exercisesRepository.deleteExercise(exercise)
    }
}
