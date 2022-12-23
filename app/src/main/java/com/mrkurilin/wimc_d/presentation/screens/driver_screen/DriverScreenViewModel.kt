package com.mrkurilin.wimc_d.presentation.screens.driver_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.model.car.Car
import com.mrkurilin.wimc_d.data.model.drive.Drive
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive
import com.mrkurilin.wimc_d.main.WimcApp

class DriverScreenViewModel(app: Application) : AndroidViewModel(app) {

    val liveCurrentStatus = MutableLiveData<String>()
    val liveDestinations = MutableLiveData<Array<String>>()
    val plannedDrivesLiveData = MutableLiveData<List<PlannedDrive>>()

    var destinations: Array<String> = arrayOf()

    private val wimcApp = app as WimcApp

    private val carsRepository = wimcApp.provideCarsRepository()
    private val currentUserCarNumber = wimcApp.provideCurrentUsersCarNumber()
    private val drivesRepository = wimcApp.provideDrivesRepository()
    private val prefixToRemove = wimcApp.resources.getString(R.string.departured_prefix)


    init {
        val destinationsRepository = wimcApp.provideDestinationsRepository()
        val plannedDrivesRepository = wimcApp.providePlannedDrivesRepository()

        plannedDrivesRepository.observePlannedDrivesList { plannedDrives ->
            plannedDrivesLiveData.postValue(plannedDrives)
        }

        destinationsRepository.observeDestinations { destinations ->
            val mappedDestinations = destinations.map { "Уехал на $it" }.toTypedArray()
            this.destinations = mappedDestinations
            val filteredDestinations = mappedDestinations.filter {
                !it.contains(liveCurrentStatus.value.toString())
            }.toTypedArray()
            liveDestinations.postValue(filteredDestinations)
        }

        carsRepository.observeCarsCurrentStatus(currentUserCarNumber) { currentStatus ->
            liveCurrentStatus.postValue(currentStatus)
        }
    }

    fun arrivedButtonPressed() {
        val currentStatus = liveCurrentStatus.value!!.removePrefix(prefixToRemove)
        val car = Car(currentUserCarNumber, currentStatus)

        carsRepository.updateCar(car)

        val filteredDestinations = destinations.filter {
            !it.contains(currentStatus)
        }.toTypedArray()
        liveDestinations.postValue(filteredDestinations)
    }

    fun destinationPressed(destination: String) {
        val to = destination.removePrefix(prefixToRemove)
        val from = liveCurrentStatus.value!!
        val drive = Drive(currentUserCarNumber, from, to)
        drivesRepository.addDrive(drive)

        val car = Car(currentUserCarNumber, destination)
        carsRepository.updateCar(car)
    }
}