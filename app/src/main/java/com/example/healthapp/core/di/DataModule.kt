package com.example.healthapp.core.di

import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class DataModule {

    private val realmVersion = 1L

    @Provides
    @Singleton
    fun provideRealmConfig () : RealmConfiguration {
        return RealmConfiguration
            .Builder()
            .schemaVersion(realmVersion)
            .build()
    }

}