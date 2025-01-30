package com.medtronic.surgery.app.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.theme.AppTheme

@Composable
fun ListErrorState(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ListErrorStatePreviewLight() {
    ListErrorStatePreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListErrorStatePreviewNight() {
    ListErrorStatePreview()
}

@Composable
private fun ListErrorStatePreview() {
    AppTheme {
        ListErrorState(errorMessage = stringResource(R.string.unknown_error))
    }
}