package com.mrkurilin.wimc_d.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrkurilin.wimc_d.data.model.drive.Drive

class DrivesRecyclerViewAdapter : RecyclerView.Adapter<DriveViewHolder>() {

    private var drives: List<Drive> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return DriveViewHolder(view)
    }

    override fun onBindViewHolder(holder: DriveViewHolder, position: Int) {
        holder.bind(drives[position])
    }

    override fun getItemCount(): Int {
        return drives.size
    }

    fun setItems(drives: List<Drive>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtils(this.drives, drives))
        this.drives = drives
        diffResult.dispatchUpdatesTo(this)
    }
}