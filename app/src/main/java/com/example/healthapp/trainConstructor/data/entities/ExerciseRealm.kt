package com.example.healthapp.trainConstructor.data.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class ExerciseRealm (

    @PrimaryKey
    var uid: String? = null,

    var trainId: String? = null,

    var name: String? = null,

    var duration: Int? = null,

    val approaches: RealmList<ApproachRealm>? = null,
) : RealmObject()