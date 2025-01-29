package com.medtronic.surgery.app.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medtronic.surgery.app.R
import com.medtronic.surgery.app.presentation.theme.AppTheme
import com.medtronic.surgery.app.presentation.theme.ColorPrimary
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
    onItemClick: (String) -> Unit,
    toggleFavorite: (String) -> Unit
) {
    val durationInMinutes = TimeUtils.convertMillisToMinutes(duration)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClick(uuid) }
    ) {
        // thumbnail
        Thumbnail(imageUrl)
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            // title
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = ColorPrimaryText
                ),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(8.dp))
            // content
            ProcedureDetails(
                uuid = uuid,
                numberOfPhases = numberOfPhases,
                durationInMinutes = durationInMinutes,
                isFavorite = isFavorite,
                toggleFavorite = toggleFavorite
            )
        }
    }
}

@Composable
private fun ProcedureDetails(
    uuid: String,
    numberOfPhases: Int,
    durationInMinutes: Int,
    isFavorite: Boolean,
    toggleFavorite: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            IconLabelItem(
                label = pluralStringResource(
                    R.plurals.number_of_phases,
                    numberOfPhases,
                    numberOfPhases
                ),
                icon = R.drawable.ic_number_of_phases
            )
            Spacer(modifier = Modifier.size(4.dp))
            IconLabelItem(
                label = pluralStringResource(
                    R.plurals.duration_in_minutes,
                    durationInMinutes,
                    durationInMinutes
                ),
                icon = R.drawable.ic_duration
            )
        }
        Image(
            modifier = Modifier.clickable {
                toggleFavorite(uuid)
            },
            painter = if (isFavorite) {
                painterResource(R.drawable.ic_favorite)
            } else {
                painterResource(R.drawable.ic_favorite_border)
            },
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = ColorPrimary)
        )
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
                onItemClick = {},
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
                onItemClick = {},
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
                onItemClick = {},
                toggleFavorite = {}
            )
        }
    }
}