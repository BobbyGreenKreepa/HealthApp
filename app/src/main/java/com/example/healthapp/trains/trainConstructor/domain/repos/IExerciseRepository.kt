package com.example.healthapp.trains.trainConstructor.domain.repos

import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import kotlinx.coroutines.flow.Flow

interface IExerciseRepository {

    suspend fun getExercisesByTrainUid(trainId: String): Flow<List<Exercise>>

    suspend fun deleteExercise(exercise: Exercise)

    suspend fun updateExercise(exercise: Exercise)

    suspend fun addExercise(exercise: Exercise)
}