package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.presentation.adapters.CarsRecyclerViewAdapter
import com.mrkurilin.wimc_d.presentation.adapters.PlannedDrivesRecyclerViewAdapter

class UserScreenFragment : Fragment(R.layout.user_screen_fragment) {

    private val viewModel by viewModels<UserScreenViewModel>()

    private lateinit var carsRecyclerView: RecyclerView
    private lateinit var planDriveButton: Button
    private lateinit var plannedDrivesRecyclerView: RecyclerView
    private lateinit var adminsButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        observeLiveData()

        carsRecyclerView.adapter = CarsRecyclerViewAdapter()

        plannedDrivesRecyclerView.adapter = PlannedDrivesRecyclerViewAdapter()

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
        adminsButton = view.findViewById(R.id.admins_button)
    }

    private fun observeLiveData() {
        viewModel.carsLiveData.observe(viewLifecycleOwner) { cars ->
            val adapter = carsRecyclerView.adapter as CarsRecyclerViewAdapter
            adapter.setItems(cars)
        }

        viewModel.plannedDrivesLiveData.observe(viewLifecycleOwner) { plannedDrives ->
            val adapter = plannedDrivesRecyclerView.adapter as PlannedDrivesRecyclerViewAdapter
            adapter.setItems(plannedDrives)
        }

        viewModel.isCurrentUserAdminLiveData.observe(viewLifecycleOwner) { isCurrentUserAdmin ->
            if (isCurrentUserAdmin) {
                setAdminsButton()
            }
        }
    }

    private fun setAdminsButton() {
        adminsButton.visibility = View.VISIBLE

        adminsButton.setOnClickListener { view ->
            showPopupAdminsMenu(view)
        }
    }

    private fun showPopupAdminsMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.admins_menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.cars_list -> {
                    return@setOnMenuItemClickListener true
                }
                R.id.drivers_list -> {
                    return@setOnMenuItemClickListener true
                }
                R.id.admins_list -> {
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    throw IllegalArgumentException("Unknown id: ${item.itemId}")
                }
            }
        }

        popupMenu.show()
    }
}