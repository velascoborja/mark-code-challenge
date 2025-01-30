package com.medtronic.surgery.app.presentation.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.theme.ColorPrimaryText

@Composable
fun IconLabelItem(
    modifier: Modifier = Modifier,
    label: String,
    @DrawableRes icon: Int
) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier
                .size(dimensionResource(R.dimen.component_icon_label_item_icon_size)),
            painter = painterResource(icon),
            contentDescription = "icon label",
            colorFilter = ColorFilter.tint(color = ColorPrimaryText)
        )

        Text(
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.component_icon_label_item_text_margin_start)),
            text = label,
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.component_icon_label_item_text_size).value.sp,
                color = ColorPrimaryText
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun IconLabelItemPreviewLight() {
    IconLabelItemPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun IconLabelItemPreviewNight() {
    IconLabelItemPreview()
}

@Composable
private fun IconLabelItemPreview() {
    AppTheme {
        IconLabelItem(
            label = "Duration",
            icon = R.drawable.ic_duration
        )
    }
}