package com.medtronic.surgery.app.presentation.ui.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.theme.AppTheme

@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    selectedFilter: FilterProcedureType,
    onApplyFilter: (FilterProcedureType) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.filter_procedure)) },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(FilterProcedureType.entries) { filter ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onApplyFilter(filter)
                                onDismiss()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = stringResource(filter.filterName)
                        )
                        if (selectedFilter == filter) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.filter_cancel))
            }
        }
    )
}

@Preview
@Composable
private fun NoFilterDialogPreview() {
    AppTheme {
        FilterDialog(
            onDismiss = {},
            selectedFilter = FilterProcedureType.NONE,
            onApplyFilter = {}
        )
    }
}

@Preview
@Composable
private fun WithFilterDialogPreview() {
    AppTheme {
        FilterDialog(
            onDismiss = {},
            selectedFilter = FilterProcedureType.DURATION_ASCENDING,
            onApplyFilter = {}
        )
    }
}