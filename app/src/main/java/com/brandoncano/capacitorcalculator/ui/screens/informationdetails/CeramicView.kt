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
import com.brandoncano.capacitorcalculator.components.InformationDetails
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppTopAppBar

@Composable
fun CeramicView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        AppTopAppBar(stringResource(R.string.ceramic_title))
        CeramicCapacitorImage()
        HeaderBodyInformation(
            header = R.string.information_details_capacitance,
            body = R.string.ceramic_capacitance_information,
            top = true,
        )
        HeaderBodyInformation(
            header =  R.string.information_details_characteristics,
            body = R.string.ceramic_characteristics_information,
        )
        HeaderBodyInformation(
            header =  R.string.information_details_applications,
            body = R.string.ceramic_applications_information,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@AppScreenPreviews
@Composable
private fun CeramicPreview() {
    InformationDetailsScreen(InformationDetails.Ceramic)
}