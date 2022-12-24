package com.mrkurilin.wimc_d.data.model.drive

import android.text.format.DateFormat
import com.mrkurilin.wimc_d.data.Constants.Companion.DATE_TIME_FORMAT
import java.util.*

data class Drive(
    val carNumber: String = "",
    val from: String = "",
    val to: String = "",
    val departureTime: String = DateFormat.format(DATE_TIME_FORMAT, Date().time).toString(),
)