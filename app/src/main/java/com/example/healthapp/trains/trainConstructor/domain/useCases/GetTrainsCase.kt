package com.example.healthapp.trains.trainConstructor.domain.useCases

import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import kotlinx.coroutines.flow.Flow

typealias GetTrainsCase = suspend () -> Flow<List<Train>>