package com.medtronic.surgery.app.data.analytics

class AnalyticsClientPropagatorImpl(
    val list: List<AnalyticsClient>
) : AnalyticsClient {

    override fun sendScreenView(screenView: AnalyticsScreenView) {
        list.forEach { it.sendScreenView(screenView) }
    }

    override fun sendEvent(event: AnalyticsEvent) {
        list.forEach { it.sendEvent(event) }
    }
}