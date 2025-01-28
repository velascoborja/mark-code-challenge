package com.medtronic.surgery.app.utils.date

import com.medtronic.surgery.app.utils.date.DateFormatter.formatDate
import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatterTest {
    @Test
    fun `formatDate should convert ISO date to DD_S_MM_S_YYYY format`() {
        val isoDate = "2025-01-28T00:00:00.000"
        val formattedDate = isoDate.formatDate(DateFormatPattern.DD_S_MM_S_YYYY)
        assertEquals("28/01/2025", formattedDate)
    }

    @Test
    fun `formatDate should handle invalid date format gracefully`() {
        val invalidDate = "invalid-date"
        val formattedDate = invalidDate.formatDate(DateFormatPattern.DD_S_MM_S_YYYY)
        assertEquals(invalidDate, formattedDate) // Should return the original string
    }

    @Test
    fun `formatDate should handle null date gracefully`() {
        val nullDate: String? = null
        val formattedDate = nullDate?.formatDate(DateFormatPattern.DD_S_MM_S_YYYY)
        assertEquals(null, formattedDate)
    }

    @Test
    fun `formatDate should handle already formatted date`() {
        val alreadyFormattedDate = "28/01/2025"
        val formattedDate = alreadyFormattedDate.formatDate(DateFormatPattern.DD_S_MM_S_YYYY)
        assertEquals(alreadyFormattedDate, formattedDate)
    }
}