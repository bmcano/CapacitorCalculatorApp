package com.brandoncano.capacitorcalculator.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.Capacitor
import com.brandoncano.capacitorcalculator.ui.composeables.AppCard
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleLargeTitle
import com.brandoncano.capacitorcalculator.ui.theme.textStyleTitle
import com.brandoncano.capacitorcalculator.ui.theme.white
import com.brandoncano.capacitorcalculator.util.formatCapacitance

@Composable
fun CapacitorLayout(capacitor: Capacitor) {
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
                text = capacitor.code + (capacitor.tolerance?.name ?: ""),
                style = textStyleLargeTitle().white()
            )
        }
        CapacitanceText(
            if (capacitor.isEmpty()) {
                stringResource(id = R.string.default_code)
            } else {
                capacitor.formatCapacitance()
            },
            capacitor.tolerance?.percentage ?: ""
        )
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

@AppComponentPreviews
@Composable
fun CapacitorLayoutPreview() {
    CapacitorCalculatorTheme {
        CapacitorLayout(capacitor = Capacitor(code = "123"))
    }
}
