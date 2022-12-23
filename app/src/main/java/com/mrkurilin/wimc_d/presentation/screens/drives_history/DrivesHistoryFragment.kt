package com.mrkurilin.wimc_d.presentation.screens.drives_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.presentation.adapters.DrivesRecyclerViewAdapter

class DrivesHistoryFragment : Fragment(R.layout.drives_history_fragment) {

    private val viewModel by viewModels<DrivesHistoryViewModel>()

    private lateinit var drivesRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        val adapter = DrivesRecyclerViewAdapter()
        drivesRecyclerView.adapter = adapter
        viewModel.drivesLiveData.observe(viewLifecycleOwner) { drives ->
            adapter.setItems(drives)
        }
    }

    fun initViews() {
        drivesRecyclerView = requireView().findViewById(R.id.drives_history_recycler_view)
    }
}