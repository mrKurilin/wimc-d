package com.mrkurilin.wimc_d.data.model.car

import android.text.format.DateFormat
import com.mrkurilin.wimc_d.data.Constants.Companion.DATE_TIME_FORMAT
import java.util.*

data class Car(
    val carNumber: String = "",
    val currentStatus: String = "",
    val changedAt: String = DateFormat.format(DATE_TIME_FORMAT, Date().time).toString(),
)