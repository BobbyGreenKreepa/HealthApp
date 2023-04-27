package com.example.healthapp.core.data

import io.realm.RealmList
import io.realm.RealmObject

interface IRealmMapper<T, R : RealmObject> {

    fun fromRealm(items: List<R>): List<T>

    fun toRealm(items: List<T>): RealmList<R>
}