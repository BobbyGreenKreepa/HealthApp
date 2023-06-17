package com.example.healthapp.core.di

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class HiltApp : Application() {

    override fun onCreate() {
        Realm.init(this)
        MapKitFactory.setApiKey("e89913a6-a5ae-45be-ba50-c70668b2f762")
        super.onCreate()
    }
}