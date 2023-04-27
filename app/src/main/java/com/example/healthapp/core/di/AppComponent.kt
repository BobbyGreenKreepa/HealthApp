package com.example.healthapp.core.di

import dagger.Component
import dagger.hilt.DefineComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, TrainModule::class])
public interface AppComponent {

}