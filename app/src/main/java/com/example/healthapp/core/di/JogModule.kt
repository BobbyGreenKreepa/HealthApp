package com.example.healthapp.core.di

import com.example.healthapp.jogConstructor.data.repos.JogsRepositoryImpl
import com.example.healthapp.jogConstructor.domain.repos.IJogsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class JogModule {

    @Binds
    @Singleton
    abstract fun provideJogRepository(jogsRepositoryImpl: JogsRepositoryImpl): IJogsRepository
}