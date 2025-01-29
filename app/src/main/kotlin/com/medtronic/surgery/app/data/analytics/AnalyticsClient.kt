package com.medtronic.surgery.app.data.analytics

interface AnalyticsClient {
    fun sendEvent(event: AnalyticsEvent)
    fun sendScreenView(screenView: AnalyticsScreenView)
}