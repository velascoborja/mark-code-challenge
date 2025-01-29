package com.medtronic.surgery.app.presentation.ui.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.medtronic.surgery.app.presentation.theme.AppTheme

@Composable
fun FavoritesListScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Favorites List Screen")
    }
}

@Preview
@Composable
private fun FavoritesListScreenPreview() {
    AppTheme {
        FavoritesListScreen(modifier = Modifier)
    }
}