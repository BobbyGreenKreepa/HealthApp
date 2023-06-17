package com.example.healthapp.jogConstructor.data.repos

import com.example.healthapp.jogConstructor.data.database.JogsDatabase
import com.example.healthapp.jogConstructor.data.mappers.layerMappers.JogLayerMapper
import com.example.healthapp.jogConstructor.domain.entities.Jog
import com.example.healthapp.jogConstructor.domain.repos.IJogsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JogsRepositoryImpl @Inject constructor(
    private val jogsDatabase: JogsDatabase,
    private val jogLayerMapper: JogLayerMapper
) : IJogsRepository {

    override suspend fun addJog(jog: Jog) {
        jogsDatabase.addTrain(jogLayerMapper.fromDomain(jog))
    }

    override suspend fun deleteJog(jog: Jog) {
        jogsDatabase.deleteJog(jogLayerMapper.fromDomain(jog))
    }

    override fun getJogs(): Flow<List<Jog>> = jogsDatabase.getJogs()
}
