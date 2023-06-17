package com.example.healthapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    private val realmVersion = 1L

    @Provides
    @Singleton
    fun provideRealmConfig () : RealmConfiguration {
        return RealmConfiguration
            .Builder()
            .schemaVersion(realmVersion)
            .deleteRealmIfMigrationNeeded()
            .build()
    }
}
