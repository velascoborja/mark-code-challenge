package com.medtronic.surgery.app.utils.time

object TimeUtils {
    fun convertMillisToMinutes(durationInMillis: Long): Int {
        return (durationInMillis / 60).toInt()
    }
}