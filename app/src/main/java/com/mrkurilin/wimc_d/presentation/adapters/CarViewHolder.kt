package com.mrkurilin.wimc_d.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.model.car.Car

class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val carNumberTextView = view.findViewById<TextView>(R.id.car_number)
    private val currentStatusTextView = view.findViewById<TextView>(R.id.current_status)
    private val changedAtTextView = view.findViewById<TextView>(R.id.changed_at)

    fun bind(car: Car) {
        carNumberTextView.text = car.carNumber
        currentStatusTextView.text = car.currentStatus
        changedAtTextView.text = car.changedAt
    }
}