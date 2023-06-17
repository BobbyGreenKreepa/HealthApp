package com.example.healthapp.jogConstructor.data.entities

import io.realm.RealmList
import io.realm.RealmObject

open class JogRealm (
    var uid: String? = null,
    var name: String? = null,
    var dataPoints: RealmList<DataPointRealm> = RealmList(),
    var distance: Int? = null
    ) : RealmObject()