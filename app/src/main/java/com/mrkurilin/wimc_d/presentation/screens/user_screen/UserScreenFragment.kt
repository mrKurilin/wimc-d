package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.presentation.adapters.CarsRecyclerViewAdapter
import com.mrkurilin.wimc_d.presentation.adapters.PlannedDrivesRecyclerViewAdapter
import com.mrkurilin.wimc_d.presentation.screens.plan_drive_screen.PlanDriveFragment

class UserScreenFragment : Fragment(R.layout.user_screen_fragment) {

    private val viewModel by viewModels<UserScreenViewModel>()

    private lateinit var carsRecyclerView: RecyclerView
    private lateinit var planDriveButton: Button
    private lateinit var plannedDrivesRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        val carsAdapter = CarsRecyclerViewAdapter()
        carsRecyclerView.adapter = carsAdapter
        viewModel.carsLiveData.observe(viewLifecycleOwner) { cars ->
            carsAdapter.setItems(cars)
        }

        val plannedDrivesAdapter = PlannedDrivesRecyclerViewAdapter()
        plannedDrivesRecyclerView.adapter = plannedDrivesAdapter
        viewModel.plannedDrivesLiveData.observe(viewLifecycleOwner) { plannedDrives ->
            plannedDrivesAdapter.setItems(plannedDrives)
        }

        planDriveButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, PlanDriveFragment())
                .commit()
        }
    }

    private fun initViews(view: View) {
        carsRecyclerView = view.findViewById(R.id.cars_recycler_view)
        planDriveButton = view.findViewById(R.id.plan_drive_button)
        plannedDrivesRecyclerView = view.findViewById(R.id.planned_drives_recycler_view)
    }
}