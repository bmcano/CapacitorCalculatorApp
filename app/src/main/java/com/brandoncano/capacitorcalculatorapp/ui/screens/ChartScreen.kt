package com.brandoncano.capacitorcalculatorapp.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.constants.CapacitorCodeConversions
import com.brandoncano.capacitorcalculatorapp.ui.components.TextBody
import com.brandoncano.capacitorcalculatorapp.ui.components.TextHeadline
import com.brandoncano.capacitorcalculatorapp.ui.components.TitleAppBar
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme

@Composable
fun ChartScreen() {
    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleAppBar(stringResource(R.string.chart_title))
                val cardModifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                val textModifier = Modifier.weight(1f).fillMaxWidth()
                Card(
                    modifier = cardModifier,
                    shape = RoundedCornerShape(2.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        TextHeadline(
                            modifier = textModifier,
                            text = "Code"
                        )
                        TextHeadline(
                            modifier = textModifier,
                            text = "pF"
                        )
                        TextHeadline(
                            modifier = textModifier,
                            text = "nF"
                        )
                        TextHeadline(
                            modifier = textModifier,
                            text = "uF"
                        )
                    }
                }
                Card(modifier = cardModifier.padding(bottom = 16.dp)) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        val codes = CapacitorCodeConversions.values().toList()
                        codes.forEach {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                TextBody(
                                    modifier = textModifier,
                                    text = it.code
                                )
                                TextBody(
                                    modifier = textModifier,
                                    text = it.pf
                                )
                                TextBody(
                                    modifier = textModifier,
                                    text = it.nf
                                )
                                TextBody(
                                    modifier = textModifier,
                                    text = it.uf
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}