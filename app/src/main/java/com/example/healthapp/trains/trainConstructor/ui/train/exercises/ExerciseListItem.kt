package com.example.healthapp.trains.trainConstructor.ui.train.exercises

import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise

data class ExerciseListItem (
    val value: Exercise,
    var expanded: Boolean
        )