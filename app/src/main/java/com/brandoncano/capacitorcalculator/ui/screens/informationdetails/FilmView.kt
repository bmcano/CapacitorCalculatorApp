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
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar

@Composable
fun FilmView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        AppTopAppBar(stringResource(R.string.film_details_title))
        // TODO - get film capacitor image(s) at some point (?)
        HeaderBodyInformation(
            topPadding = 12.dp,
            header = R.string.information_details_general_information,
            body = R.string.film_details_general_information,
        )
        HeaderBodyInformation(
            header = R.string.information_details_capacitance,
            body = R.string.film_details_capacitance,
        )
        HeaderBodyInformation(
            header = R.string.information_film_subtext_1,
            body = R.string.film_details_characteristics_1,
        )
        BodyInformation(R.string.film_details_applications_1)
        HeaderBodyInformation(
            header = R.string.information_film_subtext_2,
            body = R.string.film_details_characteristics_2,
        )
        BodyInformation(R.string.film_details_applications_2)
        HeaderBodyInformation(
            header = R.string.information_film_subtext_3,
            body = R.string.film_details_characteristics_3,
        )
        BodyInformation(R.string.film_details_applications_3)
        HeaderBodyInformation(
            header = R.string.information_film_subtext_4,
            body = R.string.film_details_characteristics_4,
        )
        BodyInformation(R.string.film_details_applications_4)
        HeaderBodyInformation(
            header = R.string.information_film_subtext_5,
            body = R.string.film_details_characteristics_5,
        )
        BodyInformation(R.string.film_details_applications_5)
        HeaderBodyInformation(
            header = R.string.information_film_subtext_6,
            body = R.string.film_details_characteristics_6,
        )
        BodyInformation(R.string.film_details_applications_6)
        HeaderBodyInformation(
            header = R.string.information_film_subtext_7,
            body = R.string.film_details_characteristics_7,
        )
        BodyInformation(R.string.film_details_applications_7)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun FilmPreview() {
    InformationDetailsScreen(InformationDetails.Film)
}
