package com.mrkurilin.wimc_d.data.model.plannedDrive

import android.text.format.DateFormat
import com.mrkurilin.wimc_d.data.Constants.Companion.DATE_TIME_FORMAT
import java.util.*

data class PlannedDrive(
    val planner: String = "",
    val from: String = "",
    val to: String = "",
    val planTime: String = "",
    val addedAt: String = DateFormat.format(DATE_TIME_FORMAT, Date().time).toString(),
)