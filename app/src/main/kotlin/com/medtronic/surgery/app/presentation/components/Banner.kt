package com.medtronic.surgery.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.medtronic.surgery.app.R

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    imageUrl?.let {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(it)
                .crossfade(true)
                .build()
        )
        Card(shape = RoundedCornerShape(dimensionResource(R.dimen.card_default_radius_corner))) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}