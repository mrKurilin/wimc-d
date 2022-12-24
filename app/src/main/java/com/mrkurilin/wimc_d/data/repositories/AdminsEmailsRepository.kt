package com.mrkurilin.wimc_d.data.repositories

interface AdminsEmailsRepository {

    fun addAdmin(email: String): Unit

    fun isAdmin(email: String): Boolean
}