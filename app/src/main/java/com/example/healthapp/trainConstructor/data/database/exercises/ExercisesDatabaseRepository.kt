package com.example.healthapp.trainConstructor.data.database.exercises

import com.example.healthapp.core.data.flowAdapter
import com.example.healthapp.trainConstructor.data.entities.ExerciseRealm
import com.example.healthapp.trainConstructor.data.mappers.realmMappers.ExerciseRealmMapper
import com.example.healthapp.trainConstructor.domain.entities.Exercise
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExercisesDatabaseRepository @Inject constructor(
    private val realmConfiguration: RealmConfiguration,
    private val exercisesMapper: ExerciseRealmMapper
) {

    fun getExercisesByTrainId(trainId: String) : Flow<List<Exercise>> = flowAdapter(exercisesMapper) {
        Realm.getInstance(realmConfiguration)
            .where(ExerciseRealm::class.java)
            .equalTo("trainId", trainId)
    }

    suspend fun updateExercise(exercise: ExerciseRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            val updatableExercise = transaction
                .where(ExerciseRealm::class.java)
                .equalTo("uid", exercise.uid)
                .findFirst()

            updatableExercise?.name = exercise.name
        }
    }

    suspend fun deleteExercise(exercise: ExerciseRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            val deletableExercise = transaction
                .where(ExerciseRealm::class.java)
                .equalTo("uid", exercise.uid)
                .findFirst()

            deletableExercise?.approaches?.deleteAllFromRealm()
            deletableExercise?.deleteFromRealm()
        }
    }

    suspend fun addExercise(exercise: ExerciseRealm) {
        val realm = Realm.getInstance(realmConfiguration)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.insert(exercise)
        }
    }
}