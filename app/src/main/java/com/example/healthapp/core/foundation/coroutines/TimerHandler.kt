package com.example.healthapp.core.foundation.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


class TimerHandler (
    externalScope: CoroutineScope,
    private val initialVal: Long = 2000
) {
    private var timer = initialVal

    private var alarmed = false

    private val _alarm = MutableSharedFlow<Unit>(replay = 0)
    val alarm: SharedFlow<Unit> = _alarm

    fun reset() {
        timer = initialVal
        alarmed = false
    }

    init {
        externalScope.launch(Dispatchers.Default) {
            while(true) {
                delay(100)
                timer -= 100

                if (timer <= 0 && !alarmed) {
                    _alarm.emit(Unit)
                    alarmed = true
                }
            }
        }
    }
}
