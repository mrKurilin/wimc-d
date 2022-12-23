package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mrkurilin.wimc_d.data.model.car.Car
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive
import com.mrkurilin.wimc_d.main.WimcApp

class UserScreenViewModel(app: Application) : AndroidViewModel(app) {

    val carsLiveData = MutableLiveData<List<Car>>()
    val plannedDrivesLiveData = MutableLiveData<List<PlannedDrive>>()

    private val wimcApp = app as WimcApp

    private val carsRepository = wimcApp.provideCarsRepository()
    private val plannedDrivesRepository = wimcApp.providePlannedDrivesRepository()

    init {
        carsRepository.observeCarsList { cars ->
            carsLiveData.postValue(cars)
        }

        plannedDrivesRepository.observePlannedDrivesList { plannedDrives ->
            plannedDrivesLiveData.postValue(plannedDrives)
        }
    }
}