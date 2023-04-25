package com.example.healthapp.trainConstructor.domain.useCases

import com.example.healthapp.trainConstructor.domain.entities.Train

typealias UpdateTrainCase = suspend (Train) -> Unit