package com.example.healthapp.trains.trainConstructor.ui.exercises

import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise

data class ExerciseListItem (
    public val value: Exercise,
    public var expanded: Boolean
        )