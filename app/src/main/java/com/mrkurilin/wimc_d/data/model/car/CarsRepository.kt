package com.mrkurilin.wimc_d.data.model.car

interface CarsRepository {

    fun addCar(car: Car): Unit

    fun deleteCar(car: Car): Unit

    fun updateCar(car: Car): Unit

    fun getCarsList(): List<Car>

    fun observeCarsList(observer: (List<Car>) -> Unit): Unit
}