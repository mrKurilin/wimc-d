package com.mrkurilin.wimc_d.data.repositories

import com.mrkurilin.wimc_d.data.model.car.Car

interface CarsRepository {

    fun addCar(car: Car): Unit

    fun deleteCar(car: Car): Unit

    fun updateCar(car: Car): Unit

    fun getCarsList(): List<Car>

    fun observeCarsList(observer: (List<Car>) -> Unit): Unit

    fun observeCarsCurrentStatus(carNumber: String, observer: (String) -> Unit)
}