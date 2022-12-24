package com.mrkurilin.wimc_d.main

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mrkurilin.wimc_d.data.repositories.*
import com.mrkurilin.wimc_d.data.repositories.firebase_repositories.*

class WimcApp : Application() {

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

    private val adminsRepository by lazy {
        AdminsFirebaseRepository()
    }

    private val driversEmailsRepository by lazy {
        DriversEmailsFirebaseRepository()
    }

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

    fun provideAdminsEmailsRepository(): AdminsEmailsRepository {
        return adminsRepository
    }

    fun provideDriversEmailsRepository(): DriversEmailsRepository {
        return driversEmailsRepository
    }
}
