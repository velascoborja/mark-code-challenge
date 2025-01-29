package com.medtronic.surgery.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.ui.procedures.ProceduresListScreen
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel

@Composable
fun MainNavigation(navController: NavHostController) {
    val proceduresListViewModel: ProceduresListViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = MainNavigationItem.ProceduresNavigationItem.route,
        route = "home_navigation"
    ) {
        composable(MainNavigationItem.ProceduresNavigationItem.route) {
            // all procedures
            ProceduresListScreen(
                proceduresListViewModel = proceduresListViewModel,
                title = stringResource(R.string.top_app_bar_all_procedures),
                emptyStateMessage = stringResource(R.string.no_procedures_found)
            )
        }
        composable(MainNavigationItem.FavoritesNavigationItem.route) {
            // favorite procedures
            ProceduresListScreen(
                proceduresListViewModel = proceduresListViewModel,
                title = stringResource(R.string.top_app_bar_favorites),
                emptyStateMessage = stringResource(R.string.no_favorites_found),
                filter = { it.isFavorite }
            )
        }
    }
}