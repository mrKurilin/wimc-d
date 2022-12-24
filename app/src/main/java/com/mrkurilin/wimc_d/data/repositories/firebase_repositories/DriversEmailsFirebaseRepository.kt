package com.mrkurilin.wimc_d.data.repositories.firebase_repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.repositories.DriversEmailsRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.DriversEmailsValueEventListener

private const val REF_DRIVERS = "drivers"

class DriversEmailsFirebaseRepository : DriversEmailsRepository {

    private var driversEmails: List<String> = listOf()
    private val driversEmailsRepository = Firebase.database.reference.child(REF_DRIVERS)

    init {
        observeDriversEmails { driversEmails ->
            this.driversEmails = driversEmails
        }
    }

    override fun addDriversEmail(email: String) {
        driversEmailsRepository.child(email.hashCode().toString()).setValue(email)
    }

    override fun getDriversEmailsList(): List<String> {
        return driversEmails
    }

    override fun isDriver(email: String): Boolean {
        return driversEmails.contains(email)
    }

    private fun observeDriversEmails(observer: (List<String>) -> Unit) {
        driversEmailsRepository.addValueEventListener(DriversEmailsValueEventListener(observer))
    }
}