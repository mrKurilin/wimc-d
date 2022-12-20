package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.DatabaseReference
import com.mrkurilin.wimc_d.data.model.plannedDrives.PlannedDrive

class FirebasePlannedDrivesListAdapter(
    activity: Activity,
    databaseReference: DatabaseReference
) : FirebaseListAdapter<PlannedDrive>(
    activity,
    PlannedDrive::class.java,
    android.R.layout.simple_list_item_1,
    databaseReference
) {
    override fun populateView(v: View, model: PlannedDrive, position: Int) {
        val at = model.planTime
        val to = model.to
        val from = model.from
        val planner = model.planner
        val text = "В $at запланирована поездка из $from на $to ($planner)"
        v.findViewById<TextView>(android.R.id.text1)?.text = text
    }
}