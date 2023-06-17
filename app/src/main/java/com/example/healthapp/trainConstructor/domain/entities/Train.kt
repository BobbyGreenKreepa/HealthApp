package com.example.healthapp.trainConstructor.domain.entities

import java.io.Serializable

data class Train (
    val uid: String,

    var name: String,

    var dateTime: String,

    var exercises: List<Exercise>
    ) : Serializable
