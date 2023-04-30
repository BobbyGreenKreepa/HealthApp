package com.example.healthapp.core.foundation.coroutines

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.launchOnStart(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted {
        this@launchOnStart.collect{}
    }
}