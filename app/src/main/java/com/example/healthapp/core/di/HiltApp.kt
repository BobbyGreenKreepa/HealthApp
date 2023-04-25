package com.example.healthapp.core.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class HiltApp : Application() {

    override fun onCreate() {
        Realm.init(this)
        super.onCreate()
    }
}