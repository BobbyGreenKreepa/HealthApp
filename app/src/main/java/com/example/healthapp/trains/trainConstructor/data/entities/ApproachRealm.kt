package com.example.healthapp.trains.trainConstructor.data.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ApproachRealm (

    @PrimaryKey
    var uid: String? = null,

    var exerciseId: String? = null,

    var index: String? = null,

    var complexity: String? = null,

    var duration: String? = null,

    var repeats: String? = null

) : RealmObject()