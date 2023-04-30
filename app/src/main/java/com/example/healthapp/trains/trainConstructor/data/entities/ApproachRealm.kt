package com.example.healthapp.trains.trainConstructor.data.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ApproachRealm (

    @PrimaryKey
    var uid: String? = null,

    var exerciseId: String? = null,

    var index: Int? = null,

    var complexity: String? = null,

    var duration: Int? = null,

    var repeats: Int? = null

) : RealmObject()