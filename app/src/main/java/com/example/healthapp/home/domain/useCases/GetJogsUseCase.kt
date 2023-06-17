package com.example.healthapp.home.domain.useCases

import com.example.healthapp.jogConstructor.domain.entities.Jog
import com.example.healthapp.jogConstructor.domain.repos.IJogsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJogsUseCase @Inject constructor(
    private val repository: IJogsRepository
) : () -> Flow<List<Jog>> {

    override fun invoke(): Flow<List<Jog>> = repository.getJogs()
}
