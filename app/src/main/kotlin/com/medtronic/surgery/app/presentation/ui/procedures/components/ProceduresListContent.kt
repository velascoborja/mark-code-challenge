package com.medtronic.surgery.app.presentation.ui.procedures.components

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.ui.procedures.ProcedureListItem
import com.medtronic.surgery.app.utils.support.Fixtures.mockProcedure

@Composable
fun ProceduresListContent(
    procedures: List<Procedure>,
    onProcedureItemClick: (String) -> Unit,
    toggleFavorite: (String) -> Unit,
) {
    LazyColumn {
        items(procedures) {
            ProcedureListItem(
                uuid = it.uuid,
                name = it.name,
                numberOfPhases = it.phases.orEmpty().count(),
                isFavorite = it.isFavorite,
                duration = it.duration,
                imageUrl = it.thumbnail?.url,
                onProcedureItemClick = onProcedureItemClick,
                toggleFavorite = toggleFavorite
            )
            HorizontalDivider()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ProceduresListScreenContentPreviewLight() {
    ProceduresListScreenContentPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProceduresListScreenContentPreviewNight() {
    ProceduresListScreenContentPreview()
}

@Composable
private fun ProceduresListScreenContentPreview() {
    AppTheme {
        val mockProcedures = (0..10).mapIndexed { _, i ->
            mockProcedure.copy(uuid = i.toString())
        }
        ProceduresListContent(
            procedures = mockProcedures,
            onProcedureItemClick = {},
            toggleFavorite = {},
        )
    }
}