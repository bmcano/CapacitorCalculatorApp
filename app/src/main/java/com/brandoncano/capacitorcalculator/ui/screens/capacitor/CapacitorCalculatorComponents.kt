package com.brandoncano.capacitorcalculator.ui.screens.capacitor

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.constants.Units
import com.brandoncano.capacitorcalculator.model.capacitor.Capacitor
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.composeables.AppCard
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.theme.textStyleSubhead
import com.brandoncano.capacitorcalculator.ui.theme.textStyleTitle
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.getTolerancePercentage
import com.brandoncano.capacitorcalculator.util.getVoltageRating

@Composable
fun TabText(@StringRes title: Int) {
    Text(
        text = stringResource(id = title),
        style = textStyleSubhead()
    )
}

@Composable
fun CapacitanceText(
    capacitor: Capacitor,
    isError: Boolean,
    isCode: Boolean,
) {
    val capacitance: String
    var tolerance = ""
    var voltage = ""
    when {
        isError -> capacitance = stringResource(id = R.string.error_na)
        isCode -> {
            capacitance = if (capacitor.isEmpty()) {
                stringResource(id = R.string.default_code)
            } else {
                capacitor.formatCapacitance()
            }
            tolerance = capacitor.getTolerancePercentage()
            voltage = capacitor.getVoltageRating()
        }
        else -> {
            capacitance = if (capacitor.isEmpty(false)) {
                stringResource(id = R.string.default_capacitance)
            } else {
                "${capacitor.capacitance} ${capacitor.units.ifEmpty { Units.PF }}"
            }
        }
    }
    AppCard(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "$capacitance $tolerance".trimEnd() + "\n$voltage".trimEnd(),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@Composable
fun ViewCommonCodeButton(navController: NavController) {
    Spacer(modifier = Modifier.height(8.dp))
    ArrowButtonCard(
        imageVector = Icons.Outlined.FileOpen,
        cardText = stringResource(id = R.string.capacitor_view_common_codes)
    ) {
        navController.navigate(Screen.Chart.route)
    }
}
