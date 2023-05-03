package com.example.healthapp.core.data

import io.realm.RealmChangeListener
import io.realm.RealmObject
import io.realm.RealmQuery
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

fun <S : RealmObject, R> flowAdapter(mapper: IRealmMapper<R, S>, query: () -> RealmQuery<S>): Flow<List<R>> = callbackFlow {

    val results = query().findAllAsync()!!
    val realm = results.realm!!

    val listener = RealmChangeListener<RealmResults<S>> { t ->
        this.trySend(realm.copyFromRealm(t)).isSuccess
    }

    results.addChangeListener(listener)

    this.trySend(realm.copyFromRealm(results)).isSuccess

    awaitClose {
        if (!realm.isClosed) {
            results.removeChangeListener(listener)
            realm.close()
        }
    }
}.flowOn(Dispatchers.Main) // listener in looper main thread
    /*.flowOn(android.os.Handler(HandlerThread("RealmDb").apply { start() }.looper).asCoroutineDispatcher("db"))*/ //  or listener in looper background thread
    .map {
        mapper.fromRealm(it)
    }
