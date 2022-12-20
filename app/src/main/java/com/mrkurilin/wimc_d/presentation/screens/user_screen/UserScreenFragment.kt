package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.presentation.screens.plan_drive_screen.PlanDriveFragment

class UserScreenFragment : Fragment(R.layout.user_screen_fragment) {

    private val viewModel by viewModels<UserScreenViewModel>()

    private lateinit var carsListView: ListView
    private lateinit var planDriveButton: Button
    private lateinit var plannedDrivesListView: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        carsListView.adapter = FirebaseCarsListAdapter(
            requireActivity(),
            viewModel.provideCarsFirebaseDatabaseReference()
        )

        plannedDrivesListView.adapter = FirebasePlannedDrivesListAdapter(
            requireActivity(),
            viewModel.providePlannedDrivesFirebaseDatabaseReference()
        )

        planDriveButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, PlanDriveFragment())
                .commit()
        }
    }

    private fun initViews(view: View) {
        carsListView = view.findViewById(R.id.current_status_listview)
        planDriveButton = view.findViewById(R.id.plan_drive_button)
        plannedDrivesListView = view.findViewById(R.id.planned_drives_listview)
    }
}