package com.mrkurilin.wimc_d.data.repositories.firebase_repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.repositories.DriversEmailsRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.DriversEmailsValueEventListener
import kotlinx.coroutines.tasks.await

private const val REF_DRIVERS = "drivers"

class DriversEmailsFirebaseRepository : DriversEmailsRepository {

    private val driversEmailsRepository = Firebase.database.reference.child(REF_DRIVERS)

    override fun addDriversEmail(email: String) {
        driversEmailsRepository.child(email.hashCode().toString()).setValue(email)
    }

    override suspend fun isDriver(email: String): Boolean {
        return DriversEmailsFirebaseRepository.isDriver(email)
    }

    override fun observeDriversEmails(observer: (List<String>) -> Unit) {
        driversEmailsRepository.addValueEventListener(DriversEmailsValueEventListener(observer))
    }

    companion object {

        suspend fun isDriver(email: String): Boolean {
            val driversEmailsRepository = Firebase.database.reference.child(REF_DRIVERS)
            val snapshot = driversEmailsRepository.get().await()
            val driversEmails = snapshot.getValue<HashMap<String, String>>()!!.values
            return driversEmails.contains(email)
        }
    }
}