package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.model.drive.Drive
import com.mrkurilin.wimc_d.data.model.drive.DrivesRepository
import com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners.DrivesValueEventListener

private const val REF_DRIVES = "drives"

class DrivesFirebaseRepository : DrivesRepository {

    private val drivesFirebaseDatabaseRef = Firebase.database.reference.child(
        REF_DRIVES
    )
    private var drives: List<Drive> = listOf()

    init {
        observeDrivesList { drives ->
            this.drives = drives
        }
    }

    override fun addDrive(drive: Drive) {
        drivesFirebaseDatabaseRef.child(drive.hashCode().toString()).setValue(drive)
    }

    override fun deleteDrive(drive: Drive) {
        drivesFirebaseDatabaseRef.child(drive.hashCode().toString()).removeValue()
    }

    override fun getDrivesList(): List<Drive> {
        return drives
    }

    override fun observeDrivesList(observer: (List<Drive>) -> Unit) {
        drivesFirebaseDatabaseRef.addValueEventListener(DrivesValueEventListener(observer))
    }
}