package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.components.CapacitorCodeConversions
import com.brandoncano.capacitorcalculator.ui.components.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.components.MenuAppBar
import com.brandoncano.capacitorcalculator.ui.components.TextBody
import com.brandoncano.capacitorcalculator.ui.components.TextLabel
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

/**
 * Job: Holds the content for the chart conversion screen
 */

@Composable
fun ChartScreen(context: Context) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) { Content(context) }
    }
}

@Composable
private fun Content(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val cardModifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        val textModifier = Modifier
            .weight(1f)
            .fillMaxWidth()
        val rowModifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        val cardLabels = listOf(
            stringResource(id = R.string.chart_code),
            stringResource(id = R.string.chart_pf),
            stringResource(id = R.string.chart_nf),
            stringResource(id = R.string.chart_uf),
        )
        val codes = CapacitorCodeConversions.entries

        MenuAppBar(stringResource(R.string.chart_title), interactionSource) {
            FeedbackMenuItem(context, interactionSource)
        }
        Card(
            modifier = cardModifier,
            shape = RoundedCornerShape(2.dp),
        ) {
            Row(modifier = rowModifier) {
                cardLabels.forEach {
                    TextLabel(
                        modifier = textModifier,
                        text = it
                    )
                }
            }
        }
        Card(
            modifier = cardModifier
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            codes.forEach {
                Row(modifier = rowModifier) {
                    it.asList().forEach { value ->
                        TextBody(
                            modifier = textModifier,
                            text = value
                        )
                    }
                }
            }
        }
    }
}