package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.presentation.adapters.PlannedDrivesRecyclerViewAdapter
import com.mrkurilin.wimc_d.presentation.screens.plan_drive_screen.PlanDriveFragment

class UserScreenFragment : Fragment(R.layout.user_screen_fragment) {

    private val viewModel by viewModels<UserScreenViewModel>()

    private lateinit var carsListView: ListView
    private lateinit var planDriveButton: Button
    private lateinit var plannedDrivesRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        carsListView.adapter = FirebaseCarsListAdapter(
            requireActivity(),
            viewModel.provideCarsFirebaseDatabaseReference()
        )

        plannedDrivesRecyclerView.adapter = PlannedDrivesRecyclerViewAdapter()

        viewModel.plannedDrivesLiveData.observe(viewLifecycleOwner) { plannedDrives ->
            (plannedDrivesRecyclerView.adapter as PlannedDrivesRecyclerViewAdapter).setItems(plannedDrives)
        }

        planDriveButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, PlanDriveFragment())
                .commit()
        }
    }

    private fun initViews(view: View) {
        carsListView = view.findViewById(R.id.current_status_list_view)
        planDriveButton = view.findViewById(R.id.plan_drive_button)
        plannedDrivesRecyclerView = view.findViewById(R.id.planned_drives_recycler_view)
    }
}