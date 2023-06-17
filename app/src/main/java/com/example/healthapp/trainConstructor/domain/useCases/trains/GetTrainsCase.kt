package com.example.healthapp.trainConstructor.domain.useCases.trains

import com.example.healthapp.trainConstructor.domain.entities.Train
import kotlinx.coroutines.flow.Flow

typealias GetTrainsCase = () -> Flow<List<Train>>