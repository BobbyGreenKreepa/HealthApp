package com.example.healthapp.trainConstructor.data.entities

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey

open class ApproachRealm (

    @PrimaryKey
    val uid: String,

    val exerciseId: String,

    val duration: Int,

    val repeats: Int,

    val exercise: RealmResults<ExerciseRealm>? = null
) : RealmObject()