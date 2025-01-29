package com.medtronic.surgery.app.presentation.ui.procedures

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.presentation.components.ListErrorState
import com.medtronic.surgery.app.presentation.components.ListLoadingState
import com.medtronic.surgery.app.presentation.components.PullToRefreshContainer
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProceduresListScreen(
    proceduresListViewModel: ProceduresListViewModel,
    title: String,
    emptyStateMessage: String,
    filter: (Procedure) -> Boolean = { true }
) {
    val proceduresListState by proceduresListViewModel.proceduresListState.collectAsStateWithLifecycle(
        initialValue = ProceduresListViewModel.ProceduresListState.Loading
    )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        },
        content = { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                when (proceduresListState) {
                    ProceduresListViewModel.ProceduresListState.Loading -> {
                        ListLoadingState()
                    }

                    is ProceduresListViewModel.ProceduresListState.Success -> {
                        val procedures =
                            (proceduresListState as ProceduresListViewModel.ProceduresListState.Success)
                                .procedures
                                .filter(filter)
                        if (procedures.isNotEmpty()) {
                            PullToRefreshContainer(
                                content = {
                                    ProceduresListContent(
                                        procedures = procedures,
                                        onItemClick = {
                                            // open procedure details in a bottom sheet here
                                        },
                                        toggleFavorite = proceduresListViewModel::toggleFavorite
                                    )
                                },
                                doOnRefresh = {
                                    proceduresListViewModel.refreshProcedures()
                                },
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
}