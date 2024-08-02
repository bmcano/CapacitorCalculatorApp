package com.brandoncano.capacitorcalculator.ui.screens.capacitorvalues

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.components.Tolerance
import com.brandoncano.capacitorcalculator.components.VoltageRating
import com.brandoncano.capacitorcalculator.ui.composeables.AppCard
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppDivider
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.gray
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.ui.theme.textStyleCallout
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline

@Composable
fun CodeExplanation() {
    Column {
        Text(
            text = stringResource(id = R.string.capacitor_values_capacitance_header),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleHeadline()
        )
        Text(
            text = stringResource(id = R.string.capacitor_values_capacitance_body),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleBody().gray()
        )
    }
}


@Composable
fun ToleranceTable() {
    Column {
        Text(
            text = stringResource(id = R.string.capacitor_values_tolerance_header),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp),
            style = textStyleHeadline()
        )
        AppCard(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)) {
            val col1 = Tolerance.getStandardToleranceLettersList()
            val col2 = Tolerance.getStandardToleranceList()
            val colHeader1 = stringResource(id = R.string.capacitor_values_tolerance_letter)
            val colHeader2 = stringResource(id = R.string.capacitor_values_tolerance_percentage)
            TableScreen(colHeader1, colHeader2, col1, col2)
        }
    }
}

@Composable
fun VoltageRatingTable() {
    Column {
        Text(
            text = stringResource(id = R.string.capacitor_values_voltage_header),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp),
            style = textStyleHeadline()
        )
        AppCard(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)) {
            val col1 = VoltageRating.getCodeList()
            val col2 = VoltageRating.getVoltageList()
            val colHeader1 = stringResource(id = R.string.capacitor_values_voltage_code)
            val colHeader2 = stringResource(id = R.string.capacitor_values_voltage_values)
            TableScreen(colHeader1, colHeader2, col1, col2)
        }
    }
}

@Composable
private fun RowScope.TableCell(
    text: String,
    weight: Float,
    style: TextStyle
) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        style = style
    )
}

@Composable
private fun TableScreen(
    columnTitle1: String,
    columnTitle2: String,
    columnContents1: List<String>,
    columnContents2: List<String>
) {
    if (columnContents1.size != columnContents2.size) return
    val tableData = List(columnContents1.size) { index ->
        columnContents1[index] to columnContents2[index]
    }
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
    ) {
        Row {
            TableCell(text = columnTitle1, weight = column1Weight, style = textStyleCallout())
            TableCell(text = columnTitle2, weight = column2Weight, style = textStyleCallout())
        }
        AppDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
        tableData.forEach { pair ->
            val (id, text) = pair
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = id, weight = column1Weight, style = textStyleBody().gray())
                TableCell(text = text, weight = column2Weight, style = textStyleBody().gray())
            }
            if (tableData[tableData.size - 1] != pair) {
                AppDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
            }
        }
    }
}

@AppComponentPreviews
@Composable
private fun TablePreview() {
    CapacitorCalculatorTheme {
        ToleranceTable()
    }
}
