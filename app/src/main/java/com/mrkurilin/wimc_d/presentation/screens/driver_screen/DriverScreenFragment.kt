package com.mrkurilin.wimc_d.presentation.screens.driver_screen

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.presentation.adapters.PlannedDrivesRecyclerViewAdapter

class DriverScreenFragment : Fragment(R.layout.driver_screen_fragment) {

    private val viewModel by viewModels<DriverScreenViewModel>()

    private lateinit var currentStatusTextView: TextView
    private lateinit var plannedDrivesRecyclerView: RecyclerView
    private lateinit var destinationsListView: ListView
    private lateinit var arrivedButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewModel.liveCurrentStatus.observe(viewLifecycleOwner) { currentStatus ->
            currentStatusTextView.text = currentStatus
        }

        val adapter = PlannedDrivesRecyclerViewAdapter()
        plannedDrivesRecyclerView.adapter = adapter
        viewModel.plannedDrivesLiveData.observe(viewLifecycleOwner) { plannedDrives ->
            adapter.setItems(plannedDrives)
        }

        viewModel.liveDestinations.observe(viewLifecycleOwner) { destinations ->
            destinationsListView.adapter = ArrayAdapter(
                requireContext(),
                R.layout.destination_list_item,
                R.id.text,
                destinations
            )
        }

        destinationsListView.setOnItemClickListener { _, itemView, _, _ ->
            val destination = itemView.findViewById<TextView>(R.id.text).text.toString()
            viewModel.departured(destination)
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
        plannedDrivesRecyclerView = requireView().findViewById(R.id.planned_drives_recycler_view)
        currentStatusTextView = requireView().findViewById(R.id.current_status)
        destinationsListView = requireView().findViewById(R.id.destinations_listview)
        arrivedButton = requireView().findViewById(R.id.arrive_btn)
    }
}