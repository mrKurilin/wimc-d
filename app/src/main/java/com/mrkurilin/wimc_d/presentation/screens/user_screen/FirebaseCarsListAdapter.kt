package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.DatabaseReference
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.model.cars.Car

class FirebaseCarsListAdapter(
    activity: Activity,
    databaseReference: DatabaseReference
) : FirebaseListAdapter<Car>(
    activity,
    Car::class.java,
    R.layout.car_list_item,
    databaseReference
) {

    override fun populateView(v: View, car: Car, position: Int) {
        v.findViewById<TextView>(R.id.car_number).text = car.carNumber
        v.findViewById<TextView>(R.id.current_status).text = car.currentStatus
        v.findViewById<TextView>(R.id.changed_at).text = car.changedAt
    }
}