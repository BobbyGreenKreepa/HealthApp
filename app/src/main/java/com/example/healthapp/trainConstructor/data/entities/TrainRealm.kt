package com.example.healthapp.trainConstructor.data.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey

open class TrainRealm (

    @PrimaryKey
    var id: String? = null,

    var name: String? = null,

    var dateTime: String? = null,

    var exercises: RealmList<ExerciseRealm>? = null
) : RealmObject()