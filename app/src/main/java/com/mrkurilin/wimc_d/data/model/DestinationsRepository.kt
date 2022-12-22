package com.mrkurilin.wimc_d.data.model

interface DestinationsRepository {

    fun addDestination(destination: String): Unit

    fun deleteDestination(destination: String): Unit

    fun getDestinationsList(): List<String>

    fun observeDestinations(observer: (String) -> Unit): Unit
}