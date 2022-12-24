package com.mrkurilin.wimc_d.data.repositories

interface DestinationsRepository {

    fun addDestination(destination: String): Unit

    fun deleteDestination(destination: String): Unit

    fun getDestinationsList(): List<String>

    fun observeDestinations(observer: (List<String>) -> Unit): Unit
}