package com.example.healthapp.core.foundation.coroutines

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<MutableList<T>>.add(value: T) {
    val list: MutableList<T> = mutableListOf()

    this.value.forEach {
        list.add(it)
    }

    list.add(value)
    this.value = list;
}

fun <T> MutableStateFlow<MutableList<T>>.remove(value: T) {
    val list: MutableList<T> = mutableListOf()
    this.value.remove(value)

    this.value.forEach {
        list.add(it)
    }

    this.value = list;
}