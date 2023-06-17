package com.example.healthapp.core.foundation.coroutines

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<List<T>>.add(value: T) {
    val list: MutableList<T> = mutableListOf()

    this.value.forEach {
        list.add(it)
    }

    list.add(value)
    this.value = list;
}

fun <T> MutableStateFlow<List<T>>.isEmpty() : Boolean {
    return value.isEmpty()
}

val <T> MutableStateFlow<List<T>>.size : Int
    get() = value.size

fun <T> MutableStateFlow<List<T>>.get(index: Int) : T {
    return value[index]
}

fun <T> MutableStateFlow<List<T>>.removeLast() {
    val list: MutableList<T> = mutableListOf()

    value.forEach {
        list.add(it)
    }

    list.removeLast()
    value = list
}

fun <T> MutableStateFlow<List<T>>.remove(value: T) {
    val list: MutableList<T> = mutableListOf()

    this.value.forEach {
        list.add(it)
    }

    list.remove(value)
    this.value = list;
}