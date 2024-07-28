package com.brandoncano.capacitorcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews

/**
 * Job: Instead of custom typography we use custom text styles for each Text() component
 * Note: Text styles
 */

@Composable
private fun textStyleBase() = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
)

@Composable
fun textStyleLargeTitle() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.largeTitle,
    lineHeight = TextLineHeight.largeTitle,
)

@Composable
fun textStyleTitle() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.title,
    lineHeight = TextLineHeight.title,
)

@Composable
fun textStyleHeadline() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.headline,
    lineHeight = TextLineHeight.headline,
    letterSpacing = 0.25.sp,
)

@Composable
fun textStyleCallout() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.callout,
    lineHeight = TextLineHeight.callout,
)

@Composable
fun textStyleBody() = textStyleBase().merge(
    fontSize = TextFontSize.body,
    lineHeight = TextLineHeight.body,
)

@Composable
fun textStyleSubhead() = textStyleBase().merge(
    fontSize = TextFontSize.subhead,
    lineHeight = TextLineHeight.subhead,
)

@Composable
fun textStyleCaption() = textStyleBase().merge(
    fontSize = TextFontSize.caption,
    lineHeight = TextLineHeight.caption,
)

/**
 * Note: Text colors
 */

@Composable
fun TextStyle.white() = this.merge(
    color = white
)

@Composable
fun TextStyle.gray() = this.merge(
    color = if (isSystemInDarkTheme()) gray_subtext_dark else gray_subtext_light
)

@AppComponentPreviews
@Composable
private fun TextStylePreview() {
    CapacitorCalculatorTheme {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "LargeTitle",
                style = textStyleLargeTitle(),
            )
            Text(
                text = "Title",
                style = textStyleTitle(),
            )
            Text(
                text = "Headline",
                style = textStyleHeadline(),
            )
            Text(
                text = "Callout",
                style = textStyleCallout(),
            )
            Text(
                text = "Body",
                style = textStyleBody(),
            )
            Text(
                text = "Body",
                style = textStyleBody().white(),
            )
            Text(
                text = "Subhead",
                style = textStyleSubhead(),
            )
            Text(
                text = "Subhead",
                style = textStyleSubhead().gray(),
            )
            Text(
                text = "Caption",
                style = textStyleCaption(),
            )
        }
    }
}
