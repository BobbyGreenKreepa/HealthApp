package com.example.healthapp.trains.trainConstructor.domain.useCases

import com.example.healthapp.trains.trainConstructor.domain.entities.Train

typealias AddTrainCase = suspend (Train) -> Unit