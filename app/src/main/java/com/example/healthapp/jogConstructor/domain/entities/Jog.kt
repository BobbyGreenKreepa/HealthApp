package com.example.healthapp.jogConstructor.domain.entities

data class Jog (
    val id: String,
    val name: String,
    val points: List<DataPoint>,
    val distance: Int
        )