package com.example.healthapp.jogConstructor.domain.repos

import com.example.healthapp.jogConstructor.domain.entities.Jog
import kotlinx.coroutines.flow.Flow

interface IJogsRepository {

    suspend fun addJog(jog: Jog)

    suspend fun deleteJog(jog: Jog)

    fun getJogs() : Flow<List<Jog>>
}