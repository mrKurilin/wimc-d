package com.mrkurilin.wimc_d.data.repositories

import com.mrkurilin.wimc_d.data.model.drive.Drive

interface DrivesRepository {

    fun addDrive(drive: Drive): Unit

    fun deleteDrive(drive: Drive): Unit

    fun observeDrivesList(observer: (List<Drive>) -> Unit): Unit
}