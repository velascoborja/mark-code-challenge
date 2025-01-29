package com.medtronic.surgery.app.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medtronic.surgery.app.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    doOnRefresh: suspend () -> Unit,
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            doOnRefresh()
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        content()
    }
}

@Preview
@Composable
private fun UICPullToRefreshContainerSampleUsage() {
    AppTheme {
        val items = remember {
            (1..50).map { "Item $it" }
        }
        PullToRefreshContainer(
            content = {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .height(200.dp)
                                .fillParentMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "You can put any content here.",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    items(items) { item ->
                        Card {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "This is item no. $item"
                            )
                        }
                    }
                }
            },
            doOnRefresh = { delay(2000) }
        )
    }
}
