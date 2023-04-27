package com.example.healthapp.trainConstructor.data.repos

import com.example.healthapp.trainConstructor.data.database.DbRepository
import com.example.healthapp.trainConstructor.data.mappers.layerMappers.TrainLayerMapper
import com.example.healthapp.trainConstructor.domain.entities.Train
import com.example.healthapp.trainConstructor.domain.repos.ITrainsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainsRepositoryImpl @Inject constructor(
    private val databaseRepo: DbRepository,
    private val trainLayerMapper: TrainLayerMapper
    ) : ITrainsRepository {

    override suspend fun getTrains(): Flow<List<Train>> {
        return databaseRepo.getTrains()
    }

    override suspend fun deleteTrain(train: Train) {
        databaseRepo.deleteTrain(trainLayerMapper.fromDomain(train))
    }

    override suspend fun updateTrain(train: Train) {
        databaseRepo.updateTrain(trainLayerMapper.fromDomain(train))
    }

    override suspend fun addTrain(train: Train) {
        databaseRepo.addTrain(trainLayerMapper.fromDomain(train))
    }
}