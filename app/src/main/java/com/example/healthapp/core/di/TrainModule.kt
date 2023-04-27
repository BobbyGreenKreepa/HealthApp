package com.example.healthapp.core.di

import com.example.healthapp.trainConstructor.data.repos.TrainsRepositoryImpl
import com.example.healthapp.trainConstructor.domain.repos.ITrainsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TrainModule {

    @Binds
    @Singleton
    abstract fun provideTrainRepository(trainsRepositoryImpl: TrainsRepositoryImpl): ITrainsRepository
}