package com.example.healthapp.trainConstructor.domain.useCases.trains

import com.example.healthapp.trainConstructor.domain.entities.Train

typealias UpdateTrainCase = suspend (Train) -> Unit