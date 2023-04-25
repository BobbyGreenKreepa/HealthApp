package com.example.healthapp.trainConstructor.domain.useCases

import com.example.healthapp.trainConstructor.domain.entities.Train
import kotlinx.coroutines.flow.Flow

typealias GetTrainsCase = suspend () -> Flow<List<Train>>