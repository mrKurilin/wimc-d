package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.Constants
import com.mrkurilin.wimc_d.data.model.cars.Car
import com.mrkurilin.wimc_d.data.model.plannedDrives.PlannedDrive
import com.mrkurilin.wimc_d.presentation.screens.plan_drive_screen.PlanDriveFragment

class UserScreenFragment : Fragment(R.layout.user_screen_fragment) {

    private val viewModel by viewModels<UserScreenViewModel>()

    private lateinit var carsListView: ListView
    private lateinit var planDriveButton: Button
    private lateinit var plannedDrivesListView: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        val databaseReference = Firebase.database.reference

        carsListView.adapter = object : FirebaseListAdapter<Car>(
            requireActivity(),
            Car::class.java,
            R.layout.car_list_item,
            viewModel.provideCarsReference()
        ) {
            override fun populateView(v: View, car: Car, position: Int) {
                v.findViewById<TextView>(R.id.car_number).text = car.carNumber
                v.findViewById<TextView>(R.id.current_status).text = car.currentStatus
                v.findViewById<TextView>(R.id.changed_at).text = car.changedAt
            }
        }

        plannedDrivesListView.adapter = object : FirebaseListAdapter<PlannedDrive>(
            requireActivity(),
            PlannedDrive::class.java,
            android.R.layout.simple_list_item_1,
            databaseReference.child(Constants.REF_PLANED_DRIVES_KEY)
        ) {
            override fun populateView(v: View?, model: PlannedDrive?, position: Int) {
                val at = model!!.planTime
                val to = model.to
                val from = model.from
                val planner = model.planner
                val text = "В $at запланирована поездка из $from на $to ($planner)"
                v?.findViewById<TextView>(android.R.id.text1)?.text = text
            }
        }

        planDriveButton.setOnClickListener {
            parentFragmentManager.beginTransaction().addToBackStack(null).replace(
                R.id.main_container,
                PlanDriveFragment()
            ).commit()
        }
    }

    private fun initViews(view: View) {
        carsListView = view.findViewById(R.id.current_status_listview)
        planDriveButton = view.findViewById(R.id.plan_drive_button)
        plannedDrivesListView = view.findViewById(R.id.planned_drives_listview)
    }
}