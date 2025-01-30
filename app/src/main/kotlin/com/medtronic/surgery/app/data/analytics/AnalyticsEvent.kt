package com.medtronic.surgery.app.data.analytics

import java.io.Serializable

open class AnalyticsEvent(
    val name: String,
    val params: MutableMap<String, Any> = mutableMapOf()
) : Serializable {

    class Key {
        companion object {
            const val UUID = "uuid"
            const val NAME = "name"
        }
    }

    fun addParameter(key: String, value: Any) {
        this.params[key] = value
    }

    companion object {
        const val EVENT_FILTER_CLICKED = "event_filter_clicked"
        const val EVENT_FILTER_TYPE_SELECTED = "event_filter_type_selected"
        const val EVENT_TOGGLE_FAVORITE = "event_toggle_favorite"
    }
}