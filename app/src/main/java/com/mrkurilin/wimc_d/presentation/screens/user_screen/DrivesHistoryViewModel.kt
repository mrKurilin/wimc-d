package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mrkurilin.wimc_d.data.model.drive.Drive
import com.mrkurilin.wimc_d.main.WimcApp

class DrivesHistoryViewModel(app: Application) : AndroidViewModel(app) {

    val drivesLiveData = MutableLiveData<List<Drive>>()

    private val wimcApp = app as WimcApp
    private val drivesRepository = wimcApp.provideDrivesRepository()

    init {
        drivesRepository.observeDrivesList { drives ->
            drivesLiveData.postValue(drives)
        }
    }
}