package com.mrkurilin.wimc_d.data.utils

import android.os.Build
import android.widget.TimePicker

class TimePickerHandler {

    companion object {

        fun getHour(timePicker: TimePicker): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour
            } else {
                timePicker.currentHour
            }
        }

        fun getMinute(timePicker: TimePicker): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.minute
            } else {
                timePicker.currentMinute
            }
        }

        fun getTime(timePicker: TimePicker): String {
            val hours = "%02d".format(getHour(timePicker))
            val minutes = "%02d".format(getMinute(timePicker))

            return "$hours:$minutes"
        }
    }
}