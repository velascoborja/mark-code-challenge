package com.medtronic.surgery.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.ui.details.ProcedureDetailsBottomSheet
import com.medtronic.surgery.app.presentation.ui.procedures.ProceduresListScreen
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel

@Composable
fun MainNavigation(navController: NavHostController) {
    val proceduresListViewModel: ProceduresListViewModel = hiltViewModel()
    var selectedProcedureUuid by remember { mutableStateOf<String?>(null) }

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
                emptyStateMessage = stringResource(R.string.no_procedures_found),
                onProcedureItemClick = { selectedProcedureUuid = it }
            )
        }
        composable(MainNavigationItem.FavoritesNavigationItem.route) {
            // favorite procedures
            ProceduresListScreen(
                proceduresListViewModel = proceduresListViewModel,
                title = stringResource(R.string.top_app_bar_favorites),
                emptyStateMessage = stringResource(R.string.no_favorites_found),
                filterFavorites = { it.isFavorite },
                onProcedureItemClick = { selectedProcedureUuid = it }
            )
        }
    }

    ProcedureDetailsBottomSheet(
        uuid = selectedProcedureUuid,
        toggleFavorite = proceduresListViewModel::toggleFavorite,
        onDismiss = { selectedProcedureUuid = null }
    )
}