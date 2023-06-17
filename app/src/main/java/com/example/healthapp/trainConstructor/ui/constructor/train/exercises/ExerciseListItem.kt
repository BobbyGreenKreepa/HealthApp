package com.example.healthapp.trainConstructor.ui.constructor.train.exercises

import com.example.healthapp.trainConstructor.domain.entities.Exercise

data class ExerciseListItem (
    val value: Exercise,
    var expanded: Boolean
        )