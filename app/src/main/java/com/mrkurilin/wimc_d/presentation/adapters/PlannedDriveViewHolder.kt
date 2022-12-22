package com.mrkurilin.wimc_d.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.data.model.plannedDrive.PlannedDrive

class PlannedDriveViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val infoTextView = view.findViewById<TextView>(android.R.id.text1)

    fun bind(plannedDrive: PlannedDrive) {
        val at = plannedDrive.planTime
        val to = plannedDrive.to
        val from = plannedDrive.from
        val planner = plannedDrive.planner
        val text = "- В $at запланирована поездка из $from на $to ($planner)"
        infoTextView.text = text
    }
}