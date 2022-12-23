package com.mrkurilin.wimc_d.data.model.drive

import android.text.format.DateFormat
import java.util.*

data class Drive(
    val carNumber: String = "",
    val from: String = "",
    val to: String = "",
    val departureTime: String = DateFormat.format("dd-MM-yyyy HH:mm:ss", Date().time).toString(),
)