package com.example.healthapp.trainConstructor.domain.entities

data class Exercise (
    val trainId: String,

    val uid: String,

    var name: String,

    var duration: Int,

    var approaches: List<Approach>
    )