package com.medtronic.surgery.app.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.medtronic.surgery.app.R

sealed class MainNavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
) {
    data object ProceduresNavigationItem : MainNavigationItem(
        route = "home",
        icon = R.drawable.ic_home,
        label = R.string.main_navigation_procedures_label,
    )

    data object FavoritesNavigationItem : MainNavigationItem(
        route = "highlights",
        icon = R.drawable.ic_favorite,
        label = R.string.main_navigation_favorites_label
    )
}