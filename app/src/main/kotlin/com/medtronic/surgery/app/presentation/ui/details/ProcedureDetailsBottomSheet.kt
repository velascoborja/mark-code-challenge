package com.medtronic.surgery.app.presentation.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import com.medtronic.surgery.app.presentation.components.Banner
import com.medtronic.surgery.app.presentation.theme.ColorPrimaryText
import com.medtronic.surgery.app.presentation.theme.ColorSecondaryText
import com.medtronic.surgery.app.presentation.ui.procedures.ProcedureDetailsItem
import com.medtronic.surgery.app.presentation.ui.procedures.ProcedurePhaseDetailsItem
import com.medtronic.surgery.app.presentation.viewmodel.details.ProcedureDetailsViewModel
import com.medtronic.surgery.app.utils.date.DateFormatPattern
import com.medtronic.surgery.app.utils.date.DateFormatter.formatDate
import com.medtronic.surgery.app.utils.time.TimeUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcedureDetailsBottomSheet(
    viewModel: ProcedureDetailsViewModel = hiltViewModel(),
    uuid: String?,
    toggleFavorite: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val procedureDetailsState by viewModel.procedureDetailsState.collectAsStateWithLifecycle()

    LaunchedEffect(uuid) {
        uuid?.let { viewModel.fetchProcedureDetails(it) }
    }

    uuid?.let {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onDismiss() }
        ) {
            when (procedureDetailsState) {
                ProcedureDetailsViewModel.ProcedureDetailsState.Loading -> {
                    BottomSheetLoadingState()
                }

                is ProcedureDetailsViewModel.ProcedureDetailsState.Error -> {
                    BottomSheetErrorState(procedureDetailsState)
                }

                is ProcedureDetailsViewModel.ProcedureDetailsState.Success -> {
                    val details =
                        (procedureDetailsState as ProcedureDetailsViewModel.ProcedureDetailsState.Success).details
                    BottomSheetMainContent(
                        details = details,
                        toggleFavorite = {
                            toggleFavorite(it)
                            viewModel.toggleFavorite(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomSheetMainContent(
    details: ProcedureDetails,
    toggleFavorite: (String) -> Unit = {}
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Banner(imageUrl = details.card?.url)
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = details.name,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = ColorPrimaryText
            ),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(
                R.string.date_published,
                details.publishedAt.formatDate(outputPattern = DateFormatPattern.DD_S_MM_S_YYYY)
            ),
            style = TextStyle(
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                color = ColorSecondaryText
            ),
        )
        Spacer(modifier = Modifier.size(16.dp))
        ProcedureDetailsItem(
            uuid = details.uuid,
            numberOfPhases = details.phases.orEmpty().count(),
            durationInMinutes = TimeUtils.convertMillisToMinutes(details.duration),
            isFavorite = details.isFavorite,
            toggleFavorite = toggleFavorite
        )

        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            text = stringResource(R.string.phases),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = ColorPrimaryText
            ),
        )
        details.phases?.let {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(details.phases) { index, phase ->
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .padding(start = dimensionResource(R.dimen.procedure_phase_thumbnail_size))
                            .padding(start = 8.dp)
                    )
                    ProcedurePhaseDetailsItem(
                        thumbnailUrl = phase.icon.url,
                        name = phase.name,
                        phaseNumber = index + 1
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(start = dimensionResource(R.dimen.procedure_phase_thumbnail_size))
                    .padding(start = 8.dp)
            )
        }
    }
}

@Composable
private fun BottomSheetErrorState(procedureDetailsState: ProcedureDetailsViewModel.ProcedureDetailsState) {
    val errorMessage =
        (procedureDetailsState as ProcedureDetailsViewModel.ProcedureDetailsState.Error).message
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.bottom_sheet_placeholder_state_height)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}

@Composable
private fun BottomSheetLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.bottom_sheet_placeholder_state_height)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(dimensionResource(R.dimen.loading_state_progress_bar_size))
        )
    }
}
