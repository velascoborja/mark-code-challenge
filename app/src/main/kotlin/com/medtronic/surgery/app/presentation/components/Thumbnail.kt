package com.medtronic.surgery.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.medtronic.surgery.app.R

@Composable
fun Thumbnail(imageUrl: String?) {
    imageUrl?.let {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(it)
                .crossfade(true)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .build()
        )
        Card(shape = RoundedCornerShape(dimensionResource(R.dimen.card_default_radius_corner))) {
            Image(
                modifier = Modifier.size(dimensionResource(R.dimen.procedure_list_item_icon_size)),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}