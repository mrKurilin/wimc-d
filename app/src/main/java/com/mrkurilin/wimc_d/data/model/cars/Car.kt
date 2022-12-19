package com.mrkurilin.wimc_d.data.model.cars

import android.text.format.DateFormat
import java.util.*

data class Car(
    val carNumber: String = "",
    val currentStatus: String = "",
    val changedAt: String = DateFormat.format("dd-MM-yyyy HH:mm:ss", Date().time).toString(),
)