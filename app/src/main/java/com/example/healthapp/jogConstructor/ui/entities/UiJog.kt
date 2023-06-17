package com.example.healthapp.jogConstructor.ui.entities

data class UiJog (
    val uid : String,
    val name : String,
    val uiDataPoints : List<UiDataPoint>,
    val distance : Int
    )