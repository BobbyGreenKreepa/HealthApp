package com.example.healthapp.trainConstructor.data.repos

import com.example.healthapp.trainConstructor.data.entities.TrainRealm
import com.example.healthapp.trainConstructor.domain.entities.Train
import com.example.healthapp.trainConstructor.domain.repos.ITrainsRepository
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainRepositoryImpl @Inject constructor(
    private val realmConfiguration: RealmConfiguration
) : ITrainsRepository {

    override suspend fun getTrains(): Flow<List<Train>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTrain(train: Train) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) {transaction ->
            val deletableTrain = transaction
                .where(TrainRealm::class.java)
                .equalTo("uid", train.uid)
                .findFirst()

            deletableTrain?.exercises?.deleteAllFromRealm()
            deletableTrain?.deleteFromRealm()
        }
    }

    override suspend fun updateTrain(train: Train) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) {transaction ->
            val updatableTrain = transaction
                .where(TrainRealm::class.java)
                .equalTo("uid", train.uid)
                .findFirst()

            updatableTrain?.name = train.name
            updatableTrain?.dateTime = train.dateTime
            udatableTrain?.p
        }
    }

    override suspend fun addTrain(train: Train) {
        TODO("Not yet implemented")
    }
}