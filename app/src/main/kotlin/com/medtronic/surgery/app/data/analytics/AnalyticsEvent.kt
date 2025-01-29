package com.medtronic.surgery.app.data.analytics

import java.io.Serializable

open class AnalyticsEvent(
    val name: String,
    val params: MutableMap<String, Any> = mutableMapOf()
) : Serializable {
    companion object {
        const val EVENT_TOGGLE_FAVORITE = "event_toggle_favorite"
    }

    class Key {
        companion object {
            const val UUID = "uuid"
            const val NAME = "name"
        }
    }

    fun addParameter(key: String, value: Any) {
        this.params[key] = value
    }
}