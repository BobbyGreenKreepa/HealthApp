package com.example.healthapp.trainConstructor.domain.repos

import com.example.healthapp.trainConstructor.domain.entities.Train
import kotlinx.coroutines.flow.Flow

interface ITrainsRepository {

    fun getTrains(): Flow<List<Train>>

    suspend fun deleteTrain(train: Train)

    suspend fun updateTrain(train: Train)

    suspend fun addTrain(train: Train)
}