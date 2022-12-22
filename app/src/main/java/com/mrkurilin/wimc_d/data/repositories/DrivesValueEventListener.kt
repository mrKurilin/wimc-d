package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.mrkurilin.wimc_d.data.model.drive.Drive

class DrivesValueEventListener(
    private val observer: (List<Drive>) -> Unit
) : ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        observer(snapshot.getValue<HashMap<String, Drive>>()!!.values.toList())
    }

    override fun onCancelled(error: DatabaseError) {
        //do nothing
    }
}