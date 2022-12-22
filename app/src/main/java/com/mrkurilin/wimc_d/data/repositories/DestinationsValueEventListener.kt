package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class DestinationsValueEventListener(
    private val observer: (List<String>) -> Unit
) : ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        observer(snapshot.getValue<HashMap<String, String>>()!!.values.toList())
    }

    override fun onCancelled(error: DatabaseError) {
        //do nothing
    }
}