package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.components.ChartRowLabels
import com.brandoncano.capacitorcalculator.ui.components.ChartTable
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

@Composable
fun ChartScreen(context: Context, navController: NavController) {
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
        modifier = Modifier.fillMaxSize(),
    ) {
        MenuTopAppBar(stringResource(R.string.chart_title), interactionSource) {
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }
        ChartRowLabels()
        ChartTable()
    }
}

@AppScreenPreviews
@Composable
private fun ChartPreview() {
    val app = MainActivity()
    ChartScreen(app, NavController(app))
}
