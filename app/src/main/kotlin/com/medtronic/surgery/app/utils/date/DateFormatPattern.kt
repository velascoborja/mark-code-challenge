package com.medtronic.surgery.app.utils.date

enum class DateFormatPattern(val format: String) {
    UTC("yyyy-MM-dd'T'HH:mm:ss.SSS"),
    DD_S_MM_S_YYYY("dd/MM/yyyy"),
}