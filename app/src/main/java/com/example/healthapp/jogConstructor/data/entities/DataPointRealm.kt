package com.example.healthapp.jogConstructor.data.entities

import io.realm.RealmObject

open class DataPointRealm (
    var data: String? = null,
    var xPoint: Double? = null,
    var yPoint: Double? = null
    ) : RealmObject()