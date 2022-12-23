package com.mrkurilin.wimc_d.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.R
import com.mrkurilin.wimc_d.data.model.drive.Drive

class DriveViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val infoTextView = view.findViewById<TextView>(android.R.id.text1)

    fun bind(drive: Drive) {
        val text = itemView.context.resources.getString(
            R.string.drive_departured,
            drive.departureTime,
            drive.carNumber,
            drive.from,
            drive.to
        )
        infoTextView.text = text
    }
}