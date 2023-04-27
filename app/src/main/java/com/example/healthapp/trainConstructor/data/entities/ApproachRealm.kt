package com.example.healthapp.trainConstructor.data.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
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