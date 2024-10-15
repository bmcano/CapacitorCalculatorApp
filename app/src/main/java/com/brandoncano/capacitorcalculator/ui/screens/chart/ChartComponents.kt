package com.brandoncano.capacitorcalculator.ui.screens.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.data.CapacitorCodeConversions
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline
import com.brandoncano.sharedcomponents.composables.AppCard
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.AppDivider

@Composable
fun ChartRowLabels() {
    val cardLabels = listOf(
        stringResource(id = R.string.chart_code),
        stringResource(id = R.string.chart_pf),
        stringResource(id = R.string.chart_nf),
        stringResource(id = R.string.chart_uf),
    )
    Card(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            cardLabels.forEach {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    style = textStyleHeadline()
                )
            }
        }
    }
}

@Composable
fun ChartTable() {
    val codes = CapacitorCodeConversions.entries
    AppCard(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp)) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            codes.forEachIndexed { index, it ->
                ChartTableRow(it)
                if (codes.size - 1 != index) {
                    AppDivider(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun ChartTableRow(row: CapacitorCodeConversions) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        row.asList().forEach { value ->
            Text(
                text = value,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                style = textStyleBody()
            )
        }
    }
}

@AppComponentPreviews
@Composable
private fun ChartRowLabelsPreview() {
    CapacitorCalculatorTheme {
        ChartRowLabels()
    }
}

@AppComponentPreviews
@Composable
private fun ChartTablePreview() {
    CapacitorCalculatorTheme {
        ChartTable()
    }
}
