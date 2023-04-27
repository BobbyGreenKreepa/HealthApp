package com.example.healthapp.trainConstructor.data.database

import com.example.healthapp.core.data.flowAdapter
import com.example.healthapp.trainConstructor.data.entities.TrainRealm
import com.example.healthapp.trainConstructor.data.mappers.realmMappers.TrainsFromRealmMapper
import com.example.healthapp.trainConstructor.domain.entities.Train
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val realmConfiguration: RealmConfiguration,
    private val trainsMapper: TrainsFromRealmMapper
) {

    fun getTrains(): Flow<List<Train>> = flowAdapter(trainsMapper) {
        Realm.getInstance(realmConfiguration).where(TrainRealm::class.java)
    }

    suspend fun deleteTrain(train: TrainRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            val deletableTrain = transaction
                .where(TrainRealm::class.java)
                .equalTo("uid", train.id)
                .findFirst()

            deletableTrain?.exercises?.deleteAllFromRealm()
            deletableTrain?.deleteFromRealm()
        }
    }

    suspend fun updateTrain(train: TrainRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            val updatableTrain = transaction
                .where(TrainRealm::class.java)
                .equalTo("uid", train.id)
                .findFirst()

            updatableTrain?.name = train.name
            updatableTrain?.dateTime = train.dateTime
        }
    }

    suspend fun addTrain(train: TrainRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.insert(train)
        }
    }
}