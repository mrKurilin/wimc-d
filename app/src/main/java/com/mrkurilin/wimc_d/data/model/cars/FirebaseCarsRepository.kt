package com.mrkurilin.wimc_d.data.model.cars

import com.google.firebase.database.DatabaseReference
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CARS_KEY

class FirebaseCarsRepository(private val rootReference: DatabaseReference) : CarsRepository {

    private val carsReference = rootReference.child(REF_CARS_KEY)

    override fun addCar(car: Car) {
        rootReference.child(REF_CARS_KEY).child(car.carNumber).setValue(car)
    }

    override fun updateCar(carNumber: String, newStatus: String) {
        TODO("Not yet implemented")
    }
}