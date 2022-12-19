package com.mrkurilin.wimc_d.data.model.plannedDrives

import android.text.format.DateFormat
import java.util.*

data class PlannedDrive(
    val planner: String = "",
    val from: String = "",
    val to: String = "",
    val planTime: String = "",
    val addedAt: String = DateFormat.format("dd-MM-yyyy HH:mm:ss", Date().time).toString(),
)