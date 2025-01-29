package com.medtronic.surgery.app.presentation.ui.procedures

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.components.Thumbnail
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.theme.ColorSecondaryText

@Composable
fun ProcedurePhaseDetailsItem(
    thumbnailUrl: String? = null,
    phaseNumber: Int,
    name: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Thumbnail(
            modifier = Modifier.size(dimensionResource(R.dimen.procedure_phase_thumbnail_size)),
            imageUrl = thumbnailUrl
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "$phaseNumber. $name",
            style = TextStyle(
                color = ColorSecondaryText,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            ),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProcedurePhaseDetailsItemPreviewLight() {
    ProcedurePhaseDetailsItemPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProcedurePhaseDetailsItemPreviewNight() {
    ProcedurePhaseDetailsItemPreview()
}

@Composable
private fun ProcedurePhaseDetailsItemPreview() {
    AppTheme {
        ProcedurePhaseDetailsItem(
            thumbnailUrl = "url",
            name = "Phase 1",
            phaseNumber = 1
        )
    }
}