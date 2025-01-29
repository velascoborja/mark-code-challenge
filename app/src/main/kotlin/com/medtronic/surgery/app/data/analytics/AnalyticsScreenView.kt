package com.medtronic.surgery.app.data.analytics

import java.io.Serializable

open class AnalyticsScreenView(
    val name: String,
    val params: MutableMap<String, Any> = mutableMapOf()
) :
    Serializable {
    companion object {
        const val SCREEN_VIEW_PROCEDURES_LIST = "screen_view_procedures_list"
        const val SCREEN_VIEW_FAVORITES_LIST = "screen_view_favorites_list"
    }

    class Key {
        companion object {
            // add more keys here if needed
        }
    }

    class Parameter {
        companion object {
            // add more parameters here if needed
        }
    }

    fun addParameter(key: String, value: Any) {
        this.params[key] = value
    }
}