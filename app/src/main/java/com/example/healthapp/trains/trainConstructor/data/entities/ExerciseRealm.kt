package com.example.healthapp.trains.trainConstructor.data.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ExerciseRealm (

    @PrimaryKey
    var uid: String? = null,

    var trainId: String? = null,

    var name: String? = null,

    var duration: String? = null,

    val approaches: RealmList<ApproachRealm>? = RealmList()
) : RealmObject()