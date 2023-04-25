package com.example.healthapp.trainConstructor.domain.useCases

import com.example.healthapp.trainConstructor.domain.entities.Train

typealias DeleteTrainCase = suspend (Train) -> Unit