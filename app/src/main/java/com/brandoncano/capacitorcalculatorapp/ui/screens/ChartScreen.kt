package com.brandoncano.capacitorcalculatorapp.ui.screens

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
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.constants.CapacitorCodeConversions
import com.brandoncano.capacitorcalculatorapp.ui.components.FeedbackMenuItem
import com.brandoncano.capacitorcalculatorapp.ui.components.MenuAppBar
import com.brandoncano.capacitorcalculatorapp.ui.components.TextBody
import com.brandoncano.capacitorcalculatorapp.ui.components.TextTitle
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme

@Composable
fun ChartScreen(context: Context) {

    val interactionSource = remember { MutableInteractionSource() }

    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),

            ) {
                MenuAppBar(stringResource(R.string.chart_title), interactionSource) {
                    FeedbackMenuItem(context, interactionSource)
                }
                val cardModifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                val textModifier = Modifier.weight(1f).fillMaxWidth()
                Card(
                    modifier = cardModifier,
                    shape = RoundedCornerShape(2.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        TextTitle(
                            modifier = textModifier,
                            text = "Code"
                        )
                        TextTitle(
                            modifier = textModifier,
                            text = "pF"
                        )
                        TextTitle(
                            modifier = textModifier,
                            text = "nF"
                        )
                        TextTitle(
                            modifier = textModifier,
                            text = "uF"
                        )
                    }
                }
                Card(
                    modifier = cardModifier
                        .padding(bottom = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    val codes = CapacitorCodeConversions.values().toList()
                    codes.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
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