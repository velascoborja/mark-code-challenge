package com.medtronic.surgery.app.presentation.ui.procedures.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.components.IconLabelItem
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.theme.ColorPrimary

@Composable
fun ProcedureDetailsItem(
    uuid: String,
    numberOfPhases: Int,
    durationInMinutes: Int,
    isFavorite: Boolean,
    toggleFavorite: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            IconLabelItem(
                label = pluralStringResource(
                    R.plurals.number_of_phases,
                    numberOfPhases,
                    numberOfPhases
                ),
                icon = R.drawable.ic_number_of_phases
            )
            Spacer(modifier = Modifier.size(4.dp))
            IconLabelItem(
                label = pluralStringResource(
                    R.plurals.duration_in_minutes,
                    durationInMinutes,
                    durationInMinutes
                ),
                icon = R.drawable.ic_duration
            )
        }
        Image(
            modifier = Modifier.clickable {
                toggleFavorite(uuid)
            },
            painter = if (isFavorite) {
                painterResource(R.drawable.ic_favorite)
            } else {
                painterResource(R.drawable.ic_favorite_border)
            },
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = ColorPrimary)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProcedureDetailsItemPreviewLight() {
    ProcedureDetailsItemPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProcedureDetailsItemPreviewNight() {
    ProcedureDetailsItemPreview()
}

@Composable
private fun ProcedureDetailsItemPreview() {
    AppTheme {
        ProcedureDetailsItem(
            uuid = "procedure-UCI_Pneumo",
            numberOfPhases = 0,
            isFavorite = false,
            durationInMinutes = 180,
            toggleFavorite = {},
        )
    }
}