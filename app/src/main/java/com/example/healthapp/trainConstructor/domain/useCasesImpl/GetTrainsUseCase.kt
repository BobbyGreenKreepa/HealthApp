package com.example.healthapp.trainConstructor.domain.useCasesImpl

import com.example.healthapp.trainConstructor.domain.entities.Train
import com.example.healthapp.trainConstructor.domain.repos.ITrainsRepository
import com.example.healthapp.trainConstructor.domain.useCases.GetTrainsCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrainsUseCase @Inject constructor(
    private val repository: ITrainsRepository
) : GetTrainsCase {

    override suspend fun invoke(): Flow<List<Train>> {
        return repository.getTrains()
    }
}