package com.medtronic.surgery.app.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.theme.AppTheme

@Composable
fun ListLoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(dimensionResource(R.dimen.loading_state_progress_bar_size))
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ListLoadingStatePreviewLight() {
    ListLoadingStatePreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListLoadingStatePreviewNight() {
    ListLoadingStatePreview()
}

@Composable
private fun ListLoadingStatePreview() {
    AppTheme {
        ListLoadingState()
    }
}