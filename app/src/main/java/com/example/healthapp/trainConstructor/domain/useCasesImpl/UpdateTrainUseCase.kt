package com.example.healthapp.trainConstructor.domain.useCasesImpl

import com.example.healthapp.trainConstructor.domain.entities.Train
import com.example.healthapp.trainConstructor.domain.repos.ITrainsRepository
import com.example.healthapp.trainConstructor.domain.useCases.UpdateTrainCase
import javax.inject.Inject

class UpdateTrainUseCase @Inject constructor(
    private val repository: ITrainsRepository
) : UpdateTrainCase {

    override suspend fun invoke(train: Train) {
        repository.updateTrain(train)
    }
}