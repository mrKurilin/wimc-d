package com.mrkurilin.wimc_d.data.repositories.firebase_repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.repositories.AdminsEmailsRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.AdminsEmailsValueEventListener

private const val REF_ADMINS = "admins"

class AdminsFirebaseRepository : AdminsEmailsRepository {

    private val adminsRepository = Firebase.database.reference.child(REF_ADMINS)

    override fun addAdmin(email: String) {
        adminsRepository.child(email.hashCode().toString()).setValue(email)
    }

    override fun observeAdminsEmails(observer: (List<String>) -> Unit) {
        adminsRepository.addValueEventListener(AdminsEmailsValueEventListener(observer))
    }
}