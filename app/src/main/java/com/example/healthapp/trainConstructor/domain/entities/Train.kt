package com.example.healthapp.trainConstructor.domain.entities

data class Train (
    val uid: String,

    var name: String,

    var dateTime: String,

    var exercises: List<Exercise>
    )
