package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CARS_KEY
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive
import com.mrkurilin.wimc_d.main.WimcApp

class UserScreenViewModel(app: Application) : AndroidViewModel(app) {

    val wimcApp = (app as WimcApp)
    val plannedDrivesLiveData = MutableLiveData<List<PlannedDrive>>()

    private val plannedDrivesRepository = wimcApp.providePlannedDrivesRepository()

    init {
        plannedDrivesRepository.observePlannedDrivesList { plannedDrives ->
            plannedDrivesLiveData.postValue(plannedDrives)
        }
    }

    fun provideCarsFirebaseDatabaseReference(): DatabaseReference {
        return Firebase.database.reference.child(REF_CARS_KEY)
    }
}