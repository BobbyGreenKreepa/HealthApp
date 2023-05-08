package com.example.healthapp.trains.trainConstructor.data.database.trains

import com.example.healthapp.core.data.flowAdapter
import com.example.healthapp.trains.trainConstructor.data.entities.TrainRealm
import com.example.healthapp.trains.trainConstructor.data.mappers.realmMappers.TrainsFromRealmMapper
import com.example.healthapp.trains.trainConstructor.domain.entities.Train
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainsDatabaseRepository @Inject constructor(
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

            deletableTrain?.exercises?.forEach { it ->
                it.approaches?.deleteAllFromRealm()
            }
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