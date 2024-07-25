package com.brandoncano.capacitorcalculator.ui.screens.smd

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
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitor
import com.brandoncano.capacitorcalculator.ui.composeables.AppCard
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleLargeTitle
import com.brandoncano.capacitorcalculator.ui.theme.textStyleTitle
import com.brandoncano.capacitorcalculator.ui.theme.white
import com.brandoncano.capacitorcalculator.util.formatCapacitance

@Composable
fun SmdCapacitorLayout(capacitor: SmdCapacitor) {
    Column(
        modifier = Modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_smd_capacitor),
                contentDescription = stringResource(id = R.string.content_description_smd_capacitor),
            )
            Text(
                text = capacitor.code,
                style = textStyleLargeTitle().white()
            )
        }
        val text = if (capacitor.isEmpty()) {
            stringResource(id = R.string.default_smd_value)
        } else {
            capacitor.formatCapacitance()
        }
        CapacitanceText(text)
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

@AppComponentPreviews
@Composable
private fun SmdCapacitorLayoutPreview() {
    CapacitorCalculatorTheme {
        Column {
            val capacitor1 = SmdCapacitor(code = "1R4")
            SmdCapacitorLayout(capacitor1)
            val capacitor2 = SmdCapacitor(code = "1R4J", navBarSelection = 1)
            SmdCapacitorLayout(capacitor2)
            val capacitor3 = SmdCapacitor(code = "A0", navBarSelection = 2)
            SmdCapacitorLayout(capacitor3)
        }
    }
}
