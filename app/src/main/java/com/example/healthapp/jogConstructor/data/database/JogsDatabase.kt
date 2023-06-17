package com.example.healthapp.jogConstructor.data.database

import com.example.healthapp.core.data.flowAdapter
import com.example.healthapp.jogConstructor.data.entities.JogRealm
import com.example.healthapp.jogConstructor.data.mappers.realmMappers.JogRealmMapper
import com.example.healthapp.jogConstructor.domain.entities.Jog
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JogsDatabase @Inject constructor(
    private val realmConfiguration: RealmConfiguration,
    private val jogRealmMapper: JogRealmMapper
) {

    fun getJogs(): Flow<List<Jog>> = flowAdapter(jogRealmMapper) {
        Realm.getInstance(realmConfiguration).where(JogRealm::class.java)
    }

    suspend fun addTrain(jog: JogRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.insert(jog)
        }
    }

    suspend fun deleteJog(jog: JogRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            val deletableJog = transaction
                .where(JogRealm::class.java)
                .equalTo("uid", jog.uid)
                .findFirst()

            deletableJog?.dataPoints?.deleteAllFromRealm()
            deletableJog?.deleteFromRealm()
        }
    }
}