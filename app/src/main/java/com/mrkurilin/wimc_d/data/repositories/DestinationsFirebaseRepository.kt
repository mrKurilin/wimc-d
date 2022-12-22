package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants
import com.mrkurilin.wimc_d.data.model.DestinationsRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.DestinationsValueEventListener

class DestinationsFirebaseRepository : DestinationsRepository {

    private val destinationsFirebaseDatabaseRef = Firebase.database.reference.child(
        Constants.REF_DESTINATIONS_KEY
    )
    private var destinations: List<String> = listOf()

    init {
        observeDestinations { destinations ->
            this.destinations = destinations
        }
    }

    override fun addDestination(destination: String) {
        val child = destinationsFirebaseDatabaseRef.child(destination.hashCode().toString())
        child.setValue(destination)
    }

    override fun deleteDestination(destination: String) {
        destinationsFirebaseDatabaseRef.child(destination.hashCode().toString()).removeValue()
    }

    override fun getDestinationsList(): List<String> {
        return destinations
    }

    override fun observeDestinations(observer: (List<String>) -> Unit) {
        destinationsFirebaseDatabaseRef.addValueEventListener(
            DestinationsValueEventListener(
                observer
            )
        )
    }
}