package com.medtronic.surgery.app.presentation.ui.procedures

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medtronic.surgery.app.presentation.components.Thumbnail
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.theme.ColorPrimaryText
import com.medtronic.surgery.app.utils.time.TimeUtils

@Composable
fun ProcedureListItem(
    modifier: Modifier = Modifier,
    uuid: String,
    name: String,
    numberOfPhases: Int,
    duration: Long,
    isFavorite: Boolean,
    imageUrl: String?,
    onProcedureItemClick: (String) -> Unit,
    toggleFavorite: (String) -> Unit
) {
    val durationInMinutes = TimeUtils.convertMillisToMinutes(duration)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onProcedureItemClick(uuid) }
    ) {
        // thumbnail
        Thumbnail(imageUrl = imageUrl)
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            // title
            Text(
                text = name,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = ColorPrimaryText
                ),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(8.dp))
            // content
            ProcedureDetailsItem(
                uuid = uuid,
                numberOfPhases = numberOfPhases,
                durationInMinutes = durationInMinutes,
                isFavorite = isFavorite,
                toggleFavorite = toggleFavorite
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProcedureListItemPreviewLight() {
    ProcedureListItemPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProcedureListItemPreviewNight() {
    ProcedureListItemPreview()
}

@Composable
private fun ProcedureListItemPreview() {
    AppTheme {
        Column {
            ProcedureListItem(
                imageUrl = "https://content.touchsurgery.com/14/3c/143cd9a4cffb00d4f891c4496fb3c0b3b0417cb61a869f92cb43155d21d04d5d?Expires=1738530720&Signature=n~mAIQTTW8sjdFqrhsrWyFVzL66poPhJB0S6-R0cSSKFHuSrbDmzy7~PDeWvy0xVyS5I9Cl2lxxmFQxZ-mtcFsMarPA59F1dQJou7nSC~u4JyfmYBYFw884YqZN0awLlGjaWwJ1AKVijwUGUVleE~3tMtVxLQghow3HrBuXzo81GH5bSERZvl3HIGVJ01jqD7GlE6GlBm0IG2vDpmQkdrXnUMrGFVF8OG7x9dUmgJAZEr3X-lKfKjDUdDmcdyWG8zo1oJn8zZxx8Q48F9umFQE2KYT7AgYAxSoRntmBUdYGGQ7P770DmRHR1AsQYerdLsCnC-DSMC9obiYiEo~2XSA__&Key-Pair-Id=KNNS9X5VSGQAG",
                uuid = "procedure-UCI_Pneumo",
                name = "Needle Aspiration for Pneumothorax",
                numberOfPhases = 0,
                isFavorite = false,
                duration = 0,
                onProcedureItemClick = {},
                toggleFavorite = {}
            )
            HorizontalDivider()
            ProcedureListItem(
                imageUrl = "https://content.touchsurgery.com/14/3c/143cd9a4cffb00d4f891c4496fb3c0b3b0417cb61a869f92cb43155d21d04d5d?Expires=1738530720&Signature=n~mAIQTTW8sjdFqrhsrWyFVzL66poPhJB0S6-R0cSSKFHuSrbDmzy7~PDeWvy0xVyS5I9Cl2lxxmFQxZ-mtcFsMarPA59F1dQJou7nSC~u4JyfmYBYFw884YqZN0awLlGjaWwJ1AKVijwUGUVleE~3tMtVxLQghow3HrBuXzo81GH5bSERZvl3HIGVJ01jqD7GlE6GlBm0IG2vDpmQkdrXnUMrGFVF8OG7x9dUmgJAZEr3X-lKfKjDUdDmcdyWG8zo1oJn8zZxx8Q48F9umFQE2KYT7AgYAxSoRntmBUdYGGQ7P770DmRHR1AsQYerdLsCnC-DSMC9obiYiEo~2XSA__&Key-Pair-Id=KNNS9X5VSGQAG",
                uuid = "procedure-UCI_Pneumo",
                name = "Needle Aspiration for Pneumothorax",
                numberOfPhases = 1,
                isFavorite = false,
                duration = 60,
                onProcedureItemClick = {},
                toggleFavorite = {}
            )
            HorizontalDivider()
            ProcedureListItem(
                imageUrl = "https://content.touchsurgery.com/14/3c/143cd9a4cffb00d4f891c4496fb3c0b3b0417cb61a869f92cb43155d21d04d5d?Expires=1738530720&Signature=n~mAIQTTW8sjdFqrhsrWyFVzL66poPhJB0S6-R0cSSKFHuSrbDmzy7~PDeWvy0xVyS5I9Cl2lxxmFQxZ-mtcFsMarPA59F1dQJou7nSC~u4JyfmYBYFw884YqZN0awLlGjaWwJ1AKVijwUGUVleE~3tMtVxLQghow3HrBuXzo81GH5bSERZvl3HIGVJ01jqD7GlE6GlBm0IG2vDpmQkdrXnUMrGFVF8OG7x9dUmgJAZEr3X-lKfKjDUdDmcdyWG8zo1oJn8zZxx8Q48F9umFQE2KYT7AgYAxSoRntmBUdYGGQ7P770DmRHR1AsQYerdLsCnC-DSMC9obiYiEo~2XSA__&Key-Pair-Id=KNNS9X5VSGQAG",
                uuid = "procedure-UCI_Pneumo",
                name = "Needle Aspiration for Pneumothorax",
                numberOfPhases = 2,
                isFavorite = false,
                duration = 1480,
                onProcedureItemClick = {},
                toggleFavorite = {}
            )
        }
    }
}