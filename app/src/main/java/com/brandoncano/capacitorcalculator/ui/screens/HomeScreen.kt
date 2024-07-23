package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.components.AppCalculatorButtons
import com.brandoncano.capacitorcalculator.ui.components.RoundAppImage
import com.brandoncano.capacitorcalculator.ui.components.OurAppsButtons
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

/**
 * Job: Holds all the content for the home screen
 */

@Composable
fun HomeScreen(
    context: Context,
    navController: NavController,
) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController)
        }
    }
}

@Composable
private fun ContentView(
    context: Context,
    navController: NavController,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuTopAppBar(stringResource(R.string.home_title), interactionSource) {
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }

        // TODO - add app icon like with resistor app
        RoundAppImage()
        AppCalculatorButtons(navController)
        OurAppsButtons(context)
    }
}

@AppScreenPreviews
@Composable
private fun HomePreview() {
    val app = MainActivity()
    HomeScreen(app, NavController(app))
}
