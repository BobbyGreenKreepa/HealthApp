package com.example.healthapp.trainConstructor.domain.useCasesImpl.trains

import com.example.healthapp.trainConstructor.domain.entities.Train
import com.example.healthapp.trainConstructor.domain.repos.ITrainsRepository
import com.example.healthapp.trainConstructor.domain.useCases.trains.DeleteTrainCase
import javax.inject.Inject

class DeleteTrainUseCase @Inject constructor(
    private val repository: ITrainsRepository
) : DeleteTrainCase {

    override suspend fun invoke(train: Train) {
        repository.deleteTrain(train)
    }
}