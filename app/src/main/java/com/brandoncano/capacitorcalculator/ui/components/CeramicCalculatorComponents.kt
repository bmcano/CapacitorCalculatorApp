package com.brandoncano.capacitorcalculator.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_capacitor),
                contentDescription = stringResource(id = R.string.content_description_app_icon),
            )
            Text(
                text = if (isError) {
                    stringResource(id = R.string.error_na)
                } else if (isCode) {
                    capacitor.code + capacitor.tolerance
                } else {
                    capacitor.formatCode()
                },
                style = textStyleLargeTitle().white()
            )
        }

        if (isError) {
            CapacitanceText(
                stringResource(id = R.string.error_na)
            )
        }
        else if (isCode) {
            CapacitanceText(
                if (capacitor.isEmpty()) {
                    stringResource(id = R.string.default_code)
                } else {
                    capacitor.formatCapacitance()
                },
                capacitor.getTolerancePercentage()
            )
        } else {
            CapacitanceText(
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
    AppCard(
        modifier = Modifier.padding(top = 12.dp)
    ) {
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
    AppCard(
        modifier = Modifier.padding(top = 12.dp)
    ) {
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
        Icons.Outlined.FileOpen,
        stringResource(id = R.string.home_view_codes)
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
            CapacitorLayout(capacitor = CeramicCapacitor(code = "126", units = "µF", tolerance = "D"))
        }

    }
}
