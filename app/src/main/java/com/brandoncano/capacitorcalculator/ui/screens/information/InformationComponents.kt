package com.brandoncano.capacitorcalculator.ui.screens.information

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.theme.textStyleCallout
import com.brandoncano.capacitorcalculator.ui.theme.textStyleSubhead
import com.brandoncano.sharedcomponents.composables.AppDivider

@Composable
fun ArrowButtonCardWithSubText(
    cardTexts: List<String>,
    subTexts: List<List<String>> = listOf(),
    onClicks: List<(() -> Unit)>,
) {
    if (cardTexts.size != subTexts.size || cardTexts.size != onClicks.size) return
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        onClicks.forEachIndexed { index, onClick ->
            CardRowViewWithSubText(onClick, cardTexts[index], subTexts[index])
            if (onClicks.size - 1 != index) {
                AppDivider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }
        }
    }
}

@Composable
private fun CardRowViewWithSubText(
    onClick: (() -> Unit),
    cardText: String,
    subText: List<String>,
) {
    Row(
        modifier = Modifier.clickable(role = Role.Button, onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
        ) {
            val bottomPadding = if (subText.isEmpty()) 16.dp else 4.dp
            Text(
                text = cardText,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = bottomPadding),
                style = textStyleCallout(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            subText.forEachIndexed { index, it ->
                val bottomDp = if (subText.size - 1 == index) 16.dp else 0.dp
                Text(
                    text = " • $it",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = bottomDp),
                    style = textStyleSubhead(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
        Image(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = stringResource(id = R.string.content_right_arrow),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
        )
    }
}
