package com.example.healthapp.trainConstructor.domain.useCases.trains

import com.example.healthapp.trainConstructor.domain.entities.Train

typealias AddTrainCase = suspend (Train) -> Unit