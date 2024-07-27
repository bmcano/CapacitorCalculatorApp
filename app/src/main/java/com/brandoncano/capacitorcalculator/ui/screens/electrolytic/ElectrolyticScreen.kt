package com.brandoncano.capacitorcalculator.ui.screens.electrolytic

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

@Composable
fun ElectrolyticScreen(context: Context, navController: NavController) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController)
        }
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        MenuTopAppBar(stringResource(R.string.electrolytic_title), interactionSource) {
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }
        ElectrolyticCapacitorImage()
        HeaderBodyInformation(
            header = R.string.electrolytic_capacitance_headline,
            body = R.string.electrolytic_capacitance_information,
        )
        HeaderBodyInformation(
            header =  R.string.electrolytic_voltage_rating_headline,
            body = R.string.electrolytic_voltage_rating_information,
        )
        HeaderBodyInformation(
            header =  R.string.electrolytic_how_its_made_headline,
            body = R.string.electrolytic_how_its_made_information,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@AppScreenPreviews
@Composable
private fun ElectrolyticPreview() {
    val app = MainActivity()
    ElectrolyticScreen(app, NavController(app))
}
