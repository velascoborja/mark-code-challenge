package com.medtronic.surgery.app.utils.date

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatter {
    fun String.formatDate(outputPattern: DateFormatPattern): String {
        return try {
            val inputFormat = SimpleDateFormat(DateFormatPattern.UTC.format, Locale.getDefault())
            val outputFormat = SimpleDateFormat(outputPattern.format, Locale.getDefault())
            val date = inputFormat.parse(this)
            date?.let { outputFormat.format(it) } ?: this
        } catch (e: Exception) {
            this // return original string if parsing fails. it's just a fallback
        }
    }
}