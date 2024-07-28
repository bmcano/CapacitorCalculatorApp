package com.brandoncano.capacitorcalculator.ui.screens.informationdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.brandoncano.capacitorcalculator.components.InformationDetails
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline

@Composable
fun InformationDetailsScreen(informationDetails: InformationDetails) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (informationDetails) {
                InformationDetails.Ceramic -> CeramicView()
                InformationDetails.Electrolytic -> ElectrolyticView()
                InformationDetails.SomethingWentWrong -> SomethingWentWrong()
            }
        }
    }
}

@Composable
fun SomethingWentWrong() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Error: something went wrong",
            style = textStyleHeadline()
        )
    }
}
