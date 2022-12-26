package com.mrkurilin.wimc_d.data.repositories.firebase_repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.repositories.DestinationsRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.DestinationsValueEventListener

private const val REF_DESTINATIONS_KEY = "destinations"

class DestinationsFirebaseRepository : DestinationsRepository {

    private val destinationsRepository = Firebase.database.reference.child(REF_DESTINATIONS_KEY)

    override fun addDestination(destination: String) {
        val child = destinationsRepository.child(destination.hashCode().toString())
        child.setValue(destination)
    }

    override fun deleteDestination(destination: String) {
        destinationsRepository.child(destination.hashCode().toString()).removeValue()
    }

    override fun observeDestinations(observer: (List<String>) -> Unit) {
        destinationsRepository.addValueEventListener(
            DestinationsValueEventListener(
                observer
            )
        )
    }
}