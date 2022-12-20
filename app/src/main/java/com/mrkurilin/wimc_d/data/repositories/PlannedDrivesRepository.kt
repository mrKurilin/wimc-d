package com.mrkurilin.wimc_d.data.repositories

import com.mrkurilin.wimc_d.data.model.plannedDrives.PlannedDrive

interface PlannedDrivesRepository {

    fun addPlannedDrive(plannedDrive: PlannedDrive): Unit
}