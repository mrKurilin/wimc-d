package com.mrkurilin.wimc_d.presentation.screens.driver_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firebase.ui.database.FirebaseListAdapter
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.model.plannedDrives.PlannedDrive

class DriverScreenFragment : Fragment(R.layout.driver_screen_fragment) {

    private val viewModel by viewModels<DriverScreenViewModel>()

    private lateinit var currentStatusTextView: TextView
    private lateinit var plannedDrivesListView: ListView
    private lateinit var destinationsListView: ListView
    private lateinit var arrivedButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewModel.observeCurrentStatus() { currentStatus ->
            currentStatusTextView.text = currentStatus
        }

        plannedDrivesListView.adapter = object : FirebaseListAdapter<PlannedDrive>(
            requireActivity(),
            PlannedDrive::class.java,
            android.R.layout.simple_list_item_1,
            viewModel.providePlannedDrivesReference()
        ) {

            override fun populateView(v: View, model: PlannedDrive, position: Int) {
                val at = model.planTime
                val from = model.from
                val to = model.to
                val planner = model.planner
                val text = resources.getString(R.string.planned_drive, at, from, to, planner)
                v.findViewById<TextView>(android.R.id.text1).text = text
            }
        }

        destinationsListView.adapter = object : FirebaseListAdapter<String>(
            requireActivity(),
            String::class.java,
            R.layout.destination_list_item,
            viewModel.provideDestinations()
        ) {

            override fun populateView(view: View, destination: String, position: Int) {
                val text = resources.getString(R.string.departured, destination)
                view.findViewById<TextView>(R.id.text).text = text
            }
        }

        destinationsListView.setOnItemClickListener { _, itemView, _, _ ->
            val currentStatus = itemView.findViewById<TextView>(R.id.text).text.toString()
            viewModel.departure(currentStatus)
            toggleVisibility()
        }

        arrivedButton.setOnClickListener {
            viewModel.arrived()
            toggleVisibility()
        }
    }

    private fun toggleVisibility() {
        val isArrivedButtonVisible = arrivedButton.isVisible
        destinationsListView.isVisible = isArrivedButtonVisible
        arrivedButton.isVisible = !isArrivedButtonVisible
    }

    private fun initViews() {
        plannedDrivesListView = requireView().findViewById(R.id.planned_drives_listview)
        currentStatusTextView = requireView().findViewById(R.id.current_status)
        destinationsListView = requireView().findViewById(R.id.destinations_listview)
        arrivedButton = requireView().findViewById(R.id.arrive_btn)
    }
}