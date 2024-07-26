package com.brandoncano.capacitorcalculator.ui.screens.ceramic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.ceramic.CeramicCapacitor
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.composeables.AppCard
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleLargeTitle
import com.brandoncano.capacitorcalculator.ui.theme.textStyleTitle
import com.brandoncano.capacitorcalculator.ui.theme.white
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.formatCode
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage

@Composable
fun CapacitorLayout(
    capacitor: CeramicCapacitor,
    isCode: Boolean = true,
    isError: Boolean = false
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.img_capacitor),
                contentDescription = stringResource(id = R.string.content_description_ceramic_capacitor),
            )
            val text = if (isError) {
                stringResource(id = R.string.error_na)
            } else if (isCode) {
                capacitor.code + capacitor.tolerance
            } else {
                capacitor.formatCode()
            }
            Text(
                text = text,
                style = textStyleLargeTitle().white()
            )
        }

        when {
            isError -> CapacitanceText(stringResource(id = R.string.error_na))
            isCode -> CapacitanceText(
                capacitance = if (capacitor.isEmpty()) {
                    stringResource(id = R.string.default_code)
                } else {
                    capacitor.formatCapacitance()
                },
                tolerance = capacitor.getTolerancePercentage()
            )
            else -> CapacitanceText(
                if (capacitor.isEmpty(false)) {
                    stringResource(id = R.string.default_capacitance)
                } else {
                    "${capacitor.capacitance} ${capacitor.units}"
                }
            )
        }
    }
}

@Composable
private fun CapacitanceText(capacitance: String, tolerance: String) {
    AppCard(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            text = "$capacitance $tolerance",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@Composable
private fun CapacitanceText(capacitance: String) {
    AppCard(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            text = capacitance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@Composable
fun ViewCommonCodeButton(navController: NavController) {
    ArrowButtonCard(
        imageVector = Icons.Outlined.FileOpen,
        cardText = stringResource(id = R.string.ceramic_view_common_codes)
    ) {
        navController.navigate(Screen.Chart.route)
    }
}

@AppComponentPreviews
@Composable
fun CapacitorLayoutPreview() {
    CapacitorCalculatorTheme {
        Column {
            CapacitorLayout(capacitor = CeramicCapacitor(code = "123"))
            CapacitorLayout(capacitor = CeramicCapacitor(code = "123", units = "nF"))
            CapacitorLayout(
                capacitor = CeramicCapacitor(
                    code = "126",
                    units = "µF",
                    tolerance = "D"
                )
            )
        }
    }
}
