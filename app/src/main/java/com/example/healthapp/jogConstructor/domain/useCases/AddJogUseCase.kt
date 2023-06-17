package com.example.healthapp.jogConstructor.domain.useCases

import com.example.healthapp.jogConstructor.domain.entities.Jog
import com.example.healthapp.jogConstructor.domain.repos.IJogsRepository
import javax.inject.Inject

class AddJogUseCase @Inject constructor(
    private val repository: IJogsRepository
) : suspend (Jog) -> Unit {

    override suspend fun invoke(jog: Jog) {
        repository.addJog(jog)
    }
}