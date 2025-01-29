package com.medtronic.surgery.app.presentation.ui.procedures

import androidx.annotation.StringRes
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.data.analytics.AnalyticsScreenView


enum class ProceduresListType(
    @StringRes val label: Int,
    @StringRes val emptyStateMessage: Int,
    val screenName: String
) {
    ALL(
        label = R.string.top_app_bar_all_procedures,
        emptyStateMessage = R.string.no_procedures_found,
        screenName = AnalyticsScreenView.SCREEN_VIEW_PROCEDURES_LIST
    ),
    FAVORITES(
        label = R.string.top_app_bar_favorites,
        emptyStateMessage = R.string.no_favorites_found,
        screenName = AnalyticsScreenView.SCREEN_VIEW_FAVORITES_LIST
    )
}