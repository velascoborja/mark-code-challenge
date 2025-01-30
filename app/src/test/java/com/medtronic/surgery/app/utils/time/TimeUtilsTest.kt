package com.medtronic.surgery.app.utils.time

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeUtilsTest {
    @Test
    fun `convertMillisToMinutes should return correct minutes`() {
        assertEquals(0, TimeUtils.convertMillisToMinutes(59))
        assertEquals(1, TimeUtils.convertMillisToMinutes(60))
        assertEquals(3, TimeUtils.convertMillisToMinutes(180))
        assertEquals(60, TimeUtils.convertMillisToMinutes(3600))
    }
}