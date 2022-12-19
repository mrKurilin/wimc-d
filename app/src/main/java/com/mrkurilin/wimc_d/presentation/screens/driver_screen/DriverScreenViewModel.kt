package com.mrkurilin.wimc_d.presentation.screens.driver_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.getValue
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CURRENT_STATUS
import com.mrkurilin.wimc_d.data.model.cars.Car
import com.mrkurilin.wimc_d.data.utils.MyValueEventListener
import com.mrkurilin.wimc_d.main.WimcApp

class DriverScreenViewModel(app: Application) : AndroidViewModel(app) {

    private val destinationsReference = (app as WimcApp).provideDestinations()
    private val plannedDrivesReference = (app as WimcApp).providePlannedDrivesReference()
    private val carsReference = (app as WimcApp).provideCarsReference()
    private val currentUserCarNumber = (app as WimcApp).provideCurrentUsersCarNumber()
    private lateinit var currentStatus: String
    private val prefixToRemove = app.resources.getString(R.string.departured_prefix)

    fun observeCurrentStatus(observer: Observer<String>) {
        carsReference.child(currentUserCarNumber).child(REF_CURRENT_STATUS)
            .addValueEventListener(object : MyValueEventListener() {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue<String>()!!
                    currentStatus = value
                    observer.onChanged(value)
                }
            })
    }

    fun arrived() {
        val currentStatus = this.currentStatus.removePrefix(prefixToRemove)
        carsReference.child(currentUserCarNumber).setValue(
            Car(
                currentUserCarNumber,
                currentStatus
            )
        )
    }

    fun departure(destination: String) {
        carsReference.child(currentUserCarNumber).child(REF_CURRENT_STATUS).setValue(destination)
    }

    fun provideDestinations() = destinationsReference

    fun providePlannedDrivesReference() = plannedDrivesReference

}