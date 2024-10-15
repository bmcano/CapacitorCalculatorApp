package com.brandoncano.capacitorcalculator.ui.screens.chart

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews

@Composable
fun ChartScreen(context: Context, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ContentView(context, navController)
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    val showMenu = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        AppMenuTopAppBar(stringResource(R.string.chart_title), interactionSource, showMenu) {
            FeedbackMenuItem(context, showMenu)
            AboutAppMenuItem(navController, showMenu)
        }
        ChartRowLabels()
        ChartTable()
    }
}

@AppScreenPreviews
@Composable
private fun ChartPreview() {
    val app = MainActivity()
    CapacitorCalculatorTheme {
        ChartScreen(app, NavController(app))
    }
}
