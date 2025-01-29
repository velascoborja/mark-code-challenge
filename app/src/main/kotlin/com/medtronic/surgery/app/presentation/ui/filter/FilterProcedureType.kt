package com.medtronic.surgery.app.presentation.ui.filter

import androidx.annotation.StringRes
import com.medtronic.surgery.app.R

enum class FilterProcedureType(
    @StringRes val filterName: Int
) {
    DURATION_ASCENDING(filterName = R.string.filter_duration_ascending),
    DURATION_DESCENDING(filterName = R.string.filter_duration_descending),
    ALPHABETICAL_ASCENDING(filterName = R.string.filter_alphabetical_ascending),
    ALPHABETICAL_DESCENDING(filterName = R.string.filter_alphabetical_descending),
    NONE(filterName = R.string.filter_none)
}