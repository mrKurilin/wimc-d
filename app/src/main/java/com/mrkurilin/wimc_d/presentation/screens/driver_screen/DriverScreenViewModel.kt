package com.mrkurilin.wimc_d.presentation.screens.driver_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.getValue
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CURRENT_STATUS
import com.mrkurilin.wimc_d.data.model.car.Car
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive
import com.mrkurilin.wimc_d.data.utils.MyValueEventListener
import com.mrkurilin.wimc_d.main.WimcApp

class DriverScreenViewModel(app: Application) : AndroidViewModel(app) {

    val liveCurrentStatus = MutableLiveData<String>()
    val liveDestinations = MutableLiveData<Array<String>>()
    val plannedDrivesLiveData = MutableLiveData<List<PlannedDrive>>()

    private val wimcApp = app as WimcApp

    private val plannedDrivesRepository = wimcApp.providePlannedDrivesRepository()
    private val destinationsReference = wimcApp.provideDestinations()
    private val carsReference = wimcApp.provideCarsReference()
    private val currentUserCarNumber = wimcApp.provideCurrentUsersCarNumber()
    private val prefixToRemove = app.resources.getString(R.string.departured_prefix)

    init {
        plannedDrivesRepository.observePlannedDrivesList { plannedDrives ->
            plannedDrivesLiveData.postValue(plannedDrives)
        }

        destinationsReference.addValueEventListener(
            object : MyValueEventListener() {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val destinations = snapshot.getValue<HashMap<String, String>>()?.values?.map {
                        "Уехал на $it"
                    }!!.toTypedArray()
                    liveDestinations.postValue(destinations)
                }
            }
        )

        carsReference.child(currentUserCarNumber).child(REF_CURRENT_STATUS).addValueEventListener(
            object : MyValueEventListener() {
                override fun onDataChange(snapshot: DataSnapshot) {
                    liveCurrentStatus.postValue(snapshot.getValue<String>()!!)
                }
            }
        )
    }

    fun arrived() {
        val currentStatus = liveCurrentStatus.value!!.removePrefix(prefixToRemove)
        val car = Car(currentUserCarNumber, currentStatus)
        carsReference.child(currentUserCarNumber).setValue(car)
    }

    fun departured(destination: String) {
        carsReference.child(currentUserCarNumber).child(REF_CURRENT_STATUS).setValue(destination)
    }
}