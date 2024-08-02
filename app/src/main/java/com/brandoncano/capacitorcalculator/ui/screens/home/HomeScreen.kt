package com.brandoncano.capacitorcalculator.ui.screens.home

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
import androidx.compose.runtime.mutableStateOf
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
import com.brandoncano.capacitorcalculator.ui.composeables.AppMenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

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
    val showMenu = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppMenuTopAppBar(stringResource(R.string.home_title), interactionSource, showMenu) {
            FeedbackMenuItem(context, showMenu)
            AboutAppMenuItem(navController, showMenu)
        }
        AppIcon()
        AppCalculatorButtons(navController)
        AppInformationScreens(navController)
        OurAppsButtons(context)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@AppScreenPreviews
@Composable
private fun HomePreview() {
    val app = MainActivity()
    HomeScreen(app, NavController(app))
}
