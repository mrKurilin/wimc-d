package com.mrkurilin.wimc_d.data.repositories.firebase_value_event_listeners

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive

class PlannedDrivesValueEventListener(
    private val observer: (List<PlannedDrive>) -> Unit
) : ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        observer(snapshot.getValue<HashMap<String, PlannedDrive>>()!!.values.toList())
    }

    override fun onCancelled(error: DatabaseError) {
        //do nothing
    }
}