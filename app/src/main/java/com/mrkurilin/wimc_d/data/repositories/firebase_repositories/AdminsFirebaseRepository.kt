package com.mrkurilin.wimc_d.data.repositories.firebase_repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.repositories.AdminsEmailsRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.AdminsValueEventListener

private const val REF_ADMINS = "admins"

class AdminsFirebaseRepository : AdminsEmailsRepository {

    private var admins: List<String> = listOf()
    private val adminsRepository = Firebase.database.reference.child(REF_ADMINS)

    init {
        observeAdminsList { admins ->
            this.admins = admins
        }
    }

    private fun observeAdminsList(observer: (List<String>) -> Unit) {
        adminsRepository.addValueEventListener(AdminsValueEventListener(observer))
    }

    override fun addAdmin(email: String) {
        adminsRepository.child(email.hashCode().toString()).setValue(email)
    }

    override fun isAdmin(email: String): Boolean {
        return admins.contains(email)
    }
}