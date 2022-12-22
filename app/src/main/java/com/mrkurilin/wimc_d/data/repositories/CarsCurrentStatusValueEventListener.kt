package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class CarsCurrentStatusValueEventListener(
    private val observer: (String) -> Unit
) : ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        val currentStatus = snapshot.getValue<String>()!!
        observer(currentStatus)
    }

    override fun onCancelled(error: DatabaseError) {
        //do nothing
    }
}