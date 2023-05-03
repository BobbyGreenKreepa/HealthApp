package com.example.healthapp.trains.trainConstructor.domain.useCasesImpl.trains

import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import com.example.healthapp.trains.trainConstructor.domain.repos.ITrainsRepository
import com.example.healthapp.trains.trainConstructor.domain.useCases.AddTrainCase
import javax.inject.Inject

open class AddTrainUseCase @Inject constructor(
    private val repository: ITrainsRepository
) : AddTrainCase {

    override suspend fun invoke(train: Train) {
        repository.addTrain(train)
    }
}