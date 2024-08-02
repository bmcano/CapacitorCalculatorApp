package com.brandoncano.capacitorcalculator.ui.screens.informationdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.navigation.InformationDetails
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline

@Composable
fun InformationDetailsScreen(informationDetails: InformationDetails) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (informationDetails) {
                InformationDetails.Ceramic -> CeramicView()
                InformationDetails.Film -> FilmView()
                InformationDetails.Electrolytic -> ElectrolyticView()
                InformationDetails.Polymer -> PolymerView()
                InformationDetails.SuperCapacitor -> SuperCapacitorView()
                InformationDetails.Mica -> MicaView()
                InformationDetails.Variable -> VariableView()
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
            text = stringResource(id = R.string.error_something_went_wrong),
            style = textStyleHeadline()
        )
    }
}

@AppScreenPreviews
@Composable
private fun SomethingWentWrongPreview() {
    InformationDetailsScreen(InformationDetails.SomethingWentWrong)
}
