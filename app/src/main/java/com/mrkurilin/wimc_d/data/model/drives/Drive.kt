package com.mrkurilin.wimc_d.data.model.drives

import android.text.format.DateFormat
import java.util.*

data class Drive(
    private val carNumber: String = "",
    private val from: String = "",
    private val to: String = "",
    private val departureTime: String =  DateFormat.format("dd-MM-yyyy HH:mm:ss", Date().time).toString(),
)