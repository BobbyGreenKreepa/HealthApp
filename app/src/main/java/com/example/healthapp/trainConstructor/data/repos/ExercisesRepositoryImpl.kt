package com.example.healthapp.trainConstructor.data.repos

import com.example.healthapp.trainConstructor.data.database.exercises.ExercisesDatabaseRepository
import com.example.healthapp.trainConstructor.data.mappers.layerMappers.ExerciseLayerMapper
import com.example.healthapp.trainConstructor.domain.entities.Exercise
import com.example.healthapp.trainConstructor.domain.repos.IExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExercisesRepositoryImpl @Inject constructor(
    private val dbRepository: ExercisesDatabaseRepository,
    private val exerciseLayerMapper: ExerciseLayerMapper
) : IExerciseRepository {

    override suspend fun getExercisesByTrainUid(trainId: String): Flow<List<Exercise>> {
        return dbRepository.getExercisesByTrainId(trainId)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        dbRepository.deleteExercise(exerciseLayerMapper.fromDomain(exercise))
    }

    override suspend fun updateExercise(exercise: Exercise) {
        dbRepository.deleteExercise(exerciseLayerMapper.fromDomain(exercise))
    }

    override suspend fun addExercise(exercise: Exercise) {
        dbRepository.addExercise(exerciseLayerMapper.fromDomain(exercise))
    }
}
