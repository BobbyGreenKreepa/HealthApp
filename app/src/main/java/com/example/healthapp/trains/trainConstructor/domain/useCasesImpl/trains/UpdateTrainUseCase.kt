package com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.trains

import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import com.example.healthapp.trains.trainConstructor.domain.repos.ITrainsRepository
import com.example.healthapp.trains.trainConstructor.domain.useCases.UpdateTrainCase
import javax.inject.Inject

class UpdateTrainUseCase @Inject constructor(
    private val repository: ITrainsRepository
) : UpdateTrainCase {

    override suspend fun invoke(train: Train) {
        repository.updateTrain(train)
    }
}