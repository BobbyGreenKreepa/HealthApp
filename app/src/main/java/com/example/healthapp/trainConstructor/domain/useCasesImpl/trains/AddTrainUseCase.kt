package com.example.healthapp.trainConstructor.domain.useCasesImpl.trains

import com.example.healthapp.trainConstructor.domain.entities.Train
import com.example.healthapp.trainConstructor.domain.repos.ITrainsRepository
import com.example.healthapp.trainConstructor.domain.useCases.trains.AddTrainCase
import javax.inject.Inject

open class AddTrainUseCase @Inject constructor(
    private val repository: ITrainsRepository
) : AddTrainCase {

    override suspend fun invoke(train: Train) {
        repository.addTrain(train)
    }
}