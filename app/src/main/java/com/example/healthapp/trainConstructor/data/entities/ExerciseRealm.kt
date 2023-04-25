package com.example.healthapp.trainConstructor.data.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class ExerciseRealm (

    @PrimaryKey
    val uid: String,

    val trainId: String,

    val name: String,

    val duration: Int,

    val approaches: RealmList<ApproachRealm>,

    @LinkingObjects("exercises")
    val train: RealmResults<TrainRealm>? = null,
) : RealmObject()