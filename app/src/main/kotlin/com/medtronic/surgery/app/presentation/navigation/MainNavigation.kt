package com.medtronic.surgery.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medtronic.surgery.app.presentation.ui.favorites.FavoritesListScreen
import com.medtronic.surgery.app.presentation.ui.procedures.ProceduresListScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainNavigationItem.ProceduresNavigationItem.route,
        route = "home_navigation"
    ) {
        composable(MainNavigationItem.ProceduresNavigationItem.route) {
            ProceduresListScreen()
        }
        composable(MainNavigationItem.FavoritesNavigationItem.route) {
            FavoritesListScreen()
        }
    }
}