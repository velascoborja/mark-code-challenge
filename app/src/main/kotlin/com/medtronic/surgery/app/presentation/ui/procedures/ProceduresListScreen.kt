package com.medtronic.surgery.app.presentation.ui.procedures

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel

@Composable
fun ProceduresListScreen(
    modifier: Modifier = Modifier,
    proceduresListViewModel: ProceduresListViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                proceduresListViewModel.fetchProcedures()
            }
        ) {
            Text(text = "Procedures List Screen")
        }
    }
}

@Preview
@Composable
private fun ProceduresScreenPreview() {
    AppTheme {
        ProceduresListScreen(modifier = Modifier)
    }
}