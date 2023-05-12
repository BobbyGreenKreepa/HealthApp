package com.example.healthapp.jogs.domain.entities

data class Jog (
    val id: String,
    val name: String,
    val coordinates: List<Coordinate>
        )