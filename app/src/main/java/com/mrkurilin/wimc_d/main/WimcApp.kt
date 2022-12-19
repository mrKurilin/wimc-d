package com.mrkurilin.wimc_d.main

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CARS_KEY
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_DESTINATIONS_KEY
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_PLANED_DRIVES_KEY

class WimcApp : Application() {


    private lateinit var carsReference: DatabaseReference
    private lateinit var plannedDrivesReference: DatabaseReference
    private lateinit var destinationsReference: DatabaseReference
    private val currentUser by lazy {
        FirebaseAuth.getInstance().currentUser!!
    }


    override fun onCreate() {
        super.onCreate()

        val firebaseDatabaseRootReference = Firebase.database.reference
        carsReference = firebaseDatabaseRootReference.child(REF_CARS_KEY)
        destinationsReference = firebaseDatabaseRootReference.child(REF_DESTINATIONS_KEY)
        plannedDrivesReference = firebaseDatabaseRootReference.child(REF_PLANED_DRIVES_KEY)
    }

    fun provideCarsReference() = carsReference

    fun provideDestinations() = destinationsReference


    fun providePlannedDrivesReference() = plannedDrivesReference

    fun provideCurrentUsersCarNumber(): String {
        return currentUser.email!!.removeSuffix("@rencons.com")
    }
}
