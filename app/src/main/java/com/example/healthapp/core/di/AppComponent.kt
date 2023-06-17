package com.example.healthapp.core.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, TrainModule::class, JogModule::class])
public interface AppComponent {

}