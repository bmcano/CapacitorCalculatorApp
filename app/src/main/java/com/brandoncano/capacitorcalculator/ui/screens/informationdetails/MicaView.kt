package com.brandoncano.capacitorcalculator.ui.screens.informationdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.navigation.InformationDetails
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppTopAppBar

@Composable
fun MicaView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        AppTopAppBar(stringResource(R.string.mica_title))
        MicaCapacitorImage()
        HeaderBodyInformation(
            topPadding = 12.dp,
            header = R.string.information_details_general_information,
            body = R.string.mica_details_general_information,
        )
        HeaderBodyInformation(
            header = R.string.information_details_capacitance,
            body = R.string.mica_details_capacitance,
        )
        HeaderBodyInformation(
            header = R.string.information_details_characteristics,
            body = R.string.mica_details_characteristics,
        )
        HeaderBodyInformation(
            header = R.string.information_details_applications,
            body = R.string.mica_details_applications,
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun MicaPreview() {
    InformationDetailsScreen(InformationDetails.Mica)
}
