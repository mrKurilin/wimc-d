package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive
import com.mrkurilin.wimc_d.data.utils.NavigationCommands
import com.mrkurilin.wimc_d.main.WimcApp

class PlanDriveViewModel(app: Application) : AndroidViewModel(app) {

    val navigation = MutableLiveData<NavigationCommands>()
    val destinationsLiveData = MutableLiveData<List<String>>()
    val toastMessage = MutableLiveData<String>()

    private val wimcApp = app as WimcApp
    private val plannedDrivesRepository = wimcApp.providePlannedDrivesRepository()
    private val destinationsRepository = wimcApp.provideDestinationsRepository()

    init {
        destinationsRepository.observeDestinations { destinations ->
            destinationsLiveData.postValue(destinations)
        }
    }

    fun okButtonPressed(to: String, from: String, time: String) {
        if (from == to) {
            toastMessage.postValue("Неверено указаны данные")
            return
        }

        val planner = wimcApp.getCurrentUser().displayName!!
        val plannedDrive = PlannedDrive(planner, from, to, time)

        plannedDrivesRepository.addPlannedDrive(plannedDrive)

        navigation.postValue(NavigationCommands.Back)
    }

    fun onCancelButtonPressed() {
        navigation.postValue(NavigationCommands.Back)
    }
}