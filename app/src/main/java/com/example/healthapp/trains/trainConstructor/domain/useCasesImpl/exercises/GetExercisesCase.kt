package com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.exercises

import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise
import com.example.healthapp.trains.trainConstructor.domain.repos.IExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExercisesCase @Inject constructor(
    private val exercisesRepository: IExerciseRepository
) : suspend (String) -> Flow<List<Exercise>> {

    override suspend fun invoke(trainId: String): Flow<List<Exercise>> {
        return exercisesRepository.getExercisesByTrainUid(trainId)
    }
}