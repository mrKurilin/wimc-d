package com.mrkurilin.wimc_d.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.model.car.Car

class CarsRecyclerViewAdapter : RecyclerView.Adapter<CarViewHolder>() {

    private var cars: List<Car> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.car_list_item, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    fun setItems(cars: List<Car>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtils(this.cars, cars))
        this.cars = cars
        diffResult.dispatchUpdatesTo(this)
    }
}