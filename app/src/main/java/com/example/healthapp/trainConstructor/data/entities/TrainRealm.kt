package com.example.healthapp.trainConstructor.data.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TrainRealm (
    @PrimaryKey
    val id: String,

    var name: String,

    var dateTime: String,

    var exercises: RealmList<ExerciseRealm> = RealmList()
) : RealmObject()