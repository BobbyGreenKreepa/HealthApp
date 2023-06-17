package com.example.healthapp.trainConstructor.domain.useCases.trains

import com.example.healthapp.trainConstructor.domain.entities.Train

typealias DeleteTrainCase = suspend (Train) -> Unit