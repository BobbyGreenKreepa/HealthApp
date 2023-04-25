package com.example.healthapp.trainConstructor.ui

import com.example.healthapp.trainConstructor.domain.useCasesImpl.AddTrainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainConstructorViewModel @Inject constructor(
    private val addTrainUseCase: AddTrainUseCase
) {

}