package com.example.healthapp.core.foundation.coroutines

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Jvm name указывается явно, из за того что с точки зрения компилятора это одинаковые функции.
 */

@JvmName("stringClear")
fun MutableStateFlow<String>.clear() {
    this.value = ""
}

@JvmName("mutableListClear")
fun <T> MutableStateFlow<MutableList<T>>.clear() {
    this.value = mutableListOf()
}