package com.mrkurilin.wimc_d.main

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CARS_KEY
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_DESTINATIONS_KEY
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_PLANED_DRIVES_KEY
import com.mrkurilin.wimc_d.data.model.DestinationsRepository
import com.mrkurilin.wimc_d.data.model.car.CarsRepository
import com.mrkurilin.wimc_d.data.model.drive.DrivesRepository
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrivesRepository
import com.mrkurilin.wimc_d.data.repositories.CarsFirebaseRepository
import com.mrkurilin.wimc_d.data.repositories.DestinationsFirebaseRepository
import com.mrkurilin.wimc_d.data.repositories.DrivesFirebaseRepository
import com.mrkurilin.wimc_d.data.repositories.PlannedDrivesFirebaseRepository

class WimcApp : Application() {

    private lateinit var carsReference: DatabaseReference
    private lateinit var plannedDrivesReference: DatabaseReference
    private lateinit var destinationsReference: DatabaseReference

    private val plannedDrivesRepository by lazy {
        PlannedDrivesFirebaseRepository()
    }

    private val carsRepository by lazy {
        CarsFirebaseRepository()
    }

    private val destinationsRepository by lazy {
        DestinationsFirebaseRepository()
    }

    private val drivesRepository by lazy {
        DrivesFirebaseRepository()
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

    fun provideCurrentUsersCarNumber(): String {
        return getCurrentUser().email!!.removeSuffix("@rencons.com")
    }

    fun getCurrentUser(): FirebaseUser {
        return FirebaseAuth.getInstance().currentUser!!
    }

    fun providePlannedDrivesRepository(): PlannedDrivesRepository {
        return plannedDrivesRepository
    }

    fun provideDrivesRepository(): DrivesRepository {
        return drivesRepository
    }

    fun provideCarsRepository(): CarsRepository {
        return carsRepository
    }

    fun provideDestinationsRepository(): DestinationsRepository {
        return destinationsRepository
    }
}
