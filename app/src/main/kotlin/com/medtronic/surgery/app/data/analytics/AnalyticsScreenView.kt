package com.medtronic.surgery.app.data.analytics

import java.io.Serializable

open class AnalyticsScreenView(
    val name: String,
    val params: MutableMap<String, Any> = mutableMapOf()
) : Serializable {

    // add more keys here if needed
    // add more parameters here if needed
    fun addParameter(key: String, value: Any) {
        this.params[key] = value
    }

    class Key {

        companion object {
        }
    }

    class Parameter {

        companion object {
        }
    }

    companion object {
        const val SCREEN_VIEW_PROCEDURES_LIST = "screen_view_procedures_list"
        const val SCREEN_VIEW_FAVORITES_LIST = "screen_view_favorites_list"
    }
}