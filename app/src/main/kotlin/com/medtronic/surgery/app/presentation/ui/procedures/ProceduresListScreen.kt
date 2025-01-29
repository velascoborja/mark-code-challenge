package com.medtronic.surgery.app.presentation.ui.procedures

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.presentation.components.ListErrorState
import com.medtronic.surgery.app.presentation.components.ListLoadingState
import com.medtronic.surgery.app.presentation.components.PullToRefreshContainer
import com.medtronic.surgery.app.presentation.components.SearchBar
import com.medtronic.surgery.app.presentation.ui.filter.FilterDialog
import com.medtronic.surgery.app.presentation.ui.filter.FilterProcedureType
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProceduresListScreen(
    proceduresListViewModel: ProceduresListViewModel,
    title: String,
    emptyStateMessage: String,
    filterFavorites: (Procedure) -> Boolean = { true },
    onProcedureItemClick: (String) -> Unit,
) {
    val proceduresListState by proceduresListViewModel
        .proceduresListState
        .collectAsStateWithLifecycle(
            initialValue = ProceduresListViewModel.ProceduresListState.Loading
        )

    val isRefreshing by proceduresListViewModel
        .isRefreshing
        .collectAsStateWithLifecycle(initialValue = false)

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showFilterDialog by rememberSaveable { mutableStateOf(false) }
    var selectedFilter by rememberSaveable { mutableStateOf(FilterProcedureType.NONE) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(title)
                    },
                    actions = {
                        IconButton(
                            modifier = Modifier.testTag("test_filter_button"),
                            onClick = { showFilterDialog = true }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_filter_procedures),
                                contentDescription = "filter"
                            )
                        }
                    }
                )
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it }
                )
            }
        },
        content = { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                when (proceduresListState) {
                    ProceduresListViewModel.ProceduresListState.Loading -> {
                        ListLoadingState()
                    }

                    is ProceduresListViewModel.ProceduresListState.Success -> {
                        val procedures =
                            (proceduresListState as ProceduresListViewModel.ProceduresListState.Success).procedures
                        val filteredProcedure = proceduresListViewModel.filterProcedures(
                            procedures = procedures,
                            searchQuery = searchQuery,
                            selectedFilter = selectedFilter,
                            filterFavorites = filterFavorites
                        )
                        if (filteredProcedure.isNotEmpty()) {
                            PullToRefreshContainer(
                                content = {
                                    ProceduresListContent(
                                        procedures = filteredProcedure,
                                        onProcedureItemClick = onProcedureItemClick,
                                        toggleFavorite = proceduresListViewModel::toggleFavorite
                                    )
                                },
                                doOnRefresh = {
                                    proceduresListViewModel.refreshProcedures()
                                },
                                isRefreshing = isRefreshing
                            )
                        } else {
                            ListErrorState(errorMessage = emptyStateMessage)
                        }
                    }

                    is ProceduresListViewModel.ProceduresListState.Error -> {
                        ListErrorState(
                            errorMessage = (proceduresListState as ProceduresListViewModel.ProceduresListState.Error).message
                        )
                    }
                }
            }
        },
    )

    if (showFilterDialog) {
        FilterDialog(
            onDismiss = { showFilterDialog = false },
            selectedFilter = selectedFilter,
            onApplyFilter = {
                selectedFilter = it
            }
        )
    }
}