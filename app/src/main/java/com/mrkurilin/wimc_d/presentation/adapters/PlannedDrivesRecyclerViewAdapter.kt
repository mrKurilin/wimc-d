package com.mrkurilin.wimc_d.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive

class PlannedDrivesRecyclerViewAdapter : RecyclerView.Adapter<PlannedDriveViewHolder>() {

    private var plannedDrives = listOf<PlannedDrive>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannedDriveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return PlannedDriveViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlannedDriveViewHolder, position: Int) {
        holder.bind(plannedDrives[position])
    }

    override fun getItemCount(): Int {
        return plannedDrives.size
    }

    fun setItems(plannedDrives: List<PlannedDrive>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtils(this.plannedDrives, plannedDrives))
        this.plannedDrives = plannedDrives
        diffResult.dispatchUpdatesTo(this)
    }
}