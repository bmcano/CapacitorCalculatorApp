package com.brandoncano.capacitorcalculator.ui.screens.standard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.standard.StandardCapacitor
import com.brandoncano.capacitorcalculator.ui.composeables.AppCard
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppStandardCard
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline
import com.brandoncano.capacitorcalculator.ui.theme.textStyleSubhead
import com.brandoncano.capacitorcalculator.ui.theme.textStyleTitle
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage
import com.brandoncano.capacitorcalculator.util.getVoltageRating

@Composable
fun CapacitorValuesText(capacitor: StandardCapacitor, isError: Boolean) {
    val capacitance = when {
        capacitor.isEmpty() -> stringResource(id = R.string.default_code)
        isError -> stringResource(id = R.string.error_invalid_code)
        else -> capacitor.formatCapacitance()
    }
    val tolerance = capacitor.getTolerancePercentage()
    val voltageRating = capacitor.getVoltageRating()
    AppCard(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {
        Text(
            text = capacitance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
        if (tolerance.isNotEmpty()) {
            Text(
                text = capacitor.getTolerancePercentage(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 6.dp, start = 12.dp, end = 12.dp),
                style = textStyleTitle(),
            )
        }
        if (voltageRating.isNotEmpty()) {
            Text(
                text = capacitor.getVoltageRating(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 6.dp, start = 12.dp, end = 12.dp),
                style = textStyleTitle(),
            )
        }
    }
}

@Composable
fun CapacitorInformation() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Text(
                text = stringResource(id = R.string.standard_calculator_information_header),
                modifier = Modifier.padding(start = 8.dp, end = 16.dp, top = 24.dp),
                style = textStyleHeadline(),
            )
        }
        AppStandardCard {
            Text(
                text = stringResource(id = R.string.standard_calculator_information_body_1),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                style = textStyleSubhead(),
            )
            Text(
                text = stringResource(id = R.string.standard_calculator_information_body_2),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                style = textStyleSubhead(),
            )
            Text(
                text = stringResource(id = R.string.standard_calculator_information_body_3),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                style = textStyleSubhead(),
            )
            Text(
                text = stringResource(id = R.string.standard_calculator_information_body_4),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                style = textStyleSubhead(),
            )
            Text(
                text = stringResource(id = R.string.standard_calculator_information_body_5),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                style = textStyleSubhead(),
            )
        }
    }
}

@AppComponentPreviews
@Composable
private fun CapacitorValuesTextPreview() {
    CapacitorCalculatorTheme {
        Column {
            CapacitorValuesText(StandardCapacitor("123"), false)
            CapacitorValuesText(StandardCapacitor("123"), true)
            CapacitorValuesText(StandardCapacitor(""), false)
            CapacitorValuesText(StandardCapacitor("123", "", "D", "1A"), false)
            CapacitorValuesText(StandardCapacitor("123", "", "", "1A"), false)
            CapacitorValuesText(StandardCapacitor("", "", "D", "1A"), false)
        }
    }
}
