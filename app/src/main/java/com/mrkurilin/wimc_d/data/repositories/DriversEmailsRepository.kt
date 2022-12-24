package com.mrkurilin.wimc_d.data.repositories

interface DriversEmailsRepository {

    fun addDriversEmail(email: String): Unit

    fun getDriversEmailsList(): List<String>

    fun isDriver(email: String): Boolean
}