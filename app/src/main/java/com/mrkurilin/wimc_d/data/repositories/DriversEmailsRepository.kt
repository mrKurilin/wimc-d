package com.mrkurilin.wimc_d.data.repositories

interface DriversEmailsRepository {

    fun addDriversEmail(email: String): Unit

    suspend fun isDriver(email: String): Boolean

    fun observeDriversEmails(observer: (List<String>) -> Unit): Unit
}