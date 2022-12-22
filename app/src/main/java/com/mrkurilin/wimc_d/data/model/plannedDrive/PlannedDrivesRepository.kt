package com.mrkurilin.wimc_d.data.model.plannedDrive

interface PlannedDrivesRepository {

    fun addPlannedDrive(plannedDrive: PlannedDrive): Unit

    fun deletePlannedDrive(plannedDrive: PlannedDrive): Unit

    fun updatePlannedDrive(plannedDrive: PlannedDrive): Unit

    fun observePlannedDrivesList(observer: (List<PlannedDrive>) -> Unit): Unit
}