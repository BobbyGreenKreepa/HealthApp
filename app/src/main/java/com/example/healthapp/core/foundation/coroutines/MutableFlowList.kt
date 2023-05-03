package com.example.healthapp.core.foundation.coroutines

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<MutableList<T>>.add(value: T) {
    val list: MutableList<T> = this.value
    list.add(value)
    this.value = list;
}

fun <T> MutableStateFlow<MutableList<T>>.remove(value: T) {
    val list: MutableList<T> = this.value
    list.remove(value)
    this.value = list;
}