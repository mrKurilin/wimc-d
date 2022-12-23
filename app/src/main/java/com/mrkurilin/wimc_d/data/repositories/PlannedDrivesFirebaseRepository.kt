package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrivesRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.PlannedDrivesValueEventListener

private const val REF_PLANED_DRIVES_KEY = "planned_drives"

class PlannedDrivesFirebaseRepository : PlannedDrivesRepository {

    private val plannedDrivesFirebaseDatabaseReference = Firebase.database.reference.child(
        REF_PLANED_DRIVES_KEY
    )

    override fun addPlannedDrive(plannedDrive: PlannedDrive) {
        plannedDrivesFirebaseDatabaseReference.child(plannedDrive.addedAt).setValue(plannedDrive)
    }

    override fun deletePlannedDrive(plannedDrive: PlannedDrive) {
        plannedDrivesFirebaseDatabaseReference.child(plannedDrive.addedAt).removeValue()
    }

    override fun updatePlannedDrive(plannedDrive: PlannedDrive) {
        plannedDrivesFirebaseDatabaseReference.child(plannedDrive.addedAt).setValue(plannedDrive)
    }

    override fun observePlannedDrivesList(observer: (List<PlannedDrive>) -> Unit) {
        plannedDrivesFirebaseDatabaseReference.addValueEventListener(
            PlannedDrivesValueEventListener(observer)
        )
    }
}