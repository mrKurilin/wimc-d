package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.mrkurilin.wimc_d.data.model.car.Car

class CarsValueEventListener(
    private val observer: (List<Car>) -> Unit
) : ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        observer(snapshot.getValue<HashMap<String, Car>>()!!.values.toList())
    }

    override fun onCancelled(error: DatabaseError) {
        //do nothing
    }
}