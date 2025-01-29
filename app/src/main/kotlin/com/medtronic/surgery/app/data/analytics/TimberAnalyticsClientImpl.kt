package com.medtronic.surgery.app.data.analytics

import timber.log.Timber

class TimberAnalyticsClientImpl : AnalyticsClient {
    override fun sendEvent(event: AnalyticsEvent) {
        Timber.d("send event: ${event.name}, params: ${event.params}")
    }

    override fun sendScreenView(screenView: AnalyticsScreenView) {
        Timber.d("view screen: ${screenView.name}, params: ${screenView.params}")
    }
}