package com.mrkurilin.wimc_d.data.repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CURRENT_STATUS
import com.mrkurilin.wimc_d.data.model.car.Car
import com.mrkurilin.wimc_d.data.model.car.CarsRepository

class CarsFirebaseRepository : CarsRepository {

    private val carsFirebaseDatabaseRef = Firebase.database.reference.child(Constants.REF_CARS_KEY)
    private var cars: List<Car> = listOf()

    init {
        observeCarsList { cars ->
            this.cars = cars
        }
    }

    override fun addCar(car: Car) {
        carsFirebaseDatabaseRef.child(car.carNumber).setValue(car)
    }

    override fun deleteCar(car: Car) {
        carsFirebaseDatabaseRef.child(car.carNumber).removeValue()
    }

    override fun updateCar(car: Car) {
        carsFirebaseDatabaseRef.child(car.carNumber).setValue(car)
    }

    override fun getCarsList(): List<Car> {
        return cars
    }

    override fun observeCarsList(observer: (List<Car>) -> Unit) {
        carsFirebaseDatabaseRef.addValueEventListener(CarsValueEventListener(observer))
    }

    override fun observeCarsCurrentStatus(carNumber: String, observer: (String) -> Unit) {
        carsFirebaseDatabaseRef.child(carNumber).child(REF_CURRENT_STATUS).addValueEventListener(
            CarsCurrentStatusValueEventListener(observer)
        )
    }
}