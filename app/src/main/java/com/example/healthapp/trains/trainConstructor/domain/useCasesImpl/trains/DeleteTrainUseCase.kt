package com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.trains

import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import com.example.healthapp.trains.trainConstructor.domain.repos.ITrainsRepository
import com.example.healthapp.trains.trainConstructor.domain.useCases.DeleteTrainCase
import javax.inject.Inject

class DeleteTrainUseCase @Inject constructor(
    private val repository: ITrainsRepository
) : DeleteTrainCase {

    override suspend fun invoke(train: Train) {
        repository.deleteTrain(train)
    }
}