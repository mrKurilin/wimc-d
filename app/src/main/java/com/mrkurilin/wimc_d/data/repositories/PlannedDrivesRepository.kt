package com.mrkurilin.wimc_d.data.repositories

import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive

interface PlannedDrivesRepository {

    fun addPlannedDrive(plannedDrive: PlannedDrive): Unit

    fun deletePlannedDrive(plannedDrive: PlannedDrive): Unit

    fun updatePlannedDrive(plannedDrive: PlannedDrive): Unit

    fun observePlannedDrivesList(observer: (List<PlannedDrive>) -> Unit): Unit
}