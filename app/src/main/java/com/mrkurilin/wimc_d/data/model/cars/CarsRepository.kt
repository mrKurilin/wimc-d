package com.mrkurilin.wimc_d.data.model.cars

interface CarsRepository {

    fun addCar(car: Car)

    fun updateCar(carNumber: String, newStatus: String)

    class Base() : CarsRepository {

        override fun addCar(car: Car) {
            TODO("Not yet implemented")
        }

        override fun updateCar(carNumber: String, newStatus: String) {
            TODO("Not yet implemented")
        }
    }
}