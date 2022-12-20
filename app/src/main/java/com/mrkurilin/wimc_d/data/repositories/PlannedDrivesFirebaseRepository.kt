package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants
import com.mrkurilin.wimc_d.data.model.plannedDrives.PlannedDrive

class PlannedDrivesFirebaseRepository : PlannedDrivesRepository {

    private val plannedDrivesFirebaseDatabaseReference = Firebase.database.reference.child(
        Constants.REF_PLANED_DRIVES_KEY
    )

    override fun addPlannedDrive(plannedDrive: PlannedDrive) {
        plannedDrivesFirebaseDatabaseReference.push().setValue(plannedDrive)
    }
}