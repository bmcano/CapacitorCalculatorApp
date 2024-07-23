package com.brandoncano.capacitorcalculator.navigation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.capacitorcalculator.model.ceramic.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.model.CeramicCapacitorViewModel
import com.brandoncano.capacitorcalculator.ui.screens.AboutScreen
import com.brandoncano.capacitorcalculator.ui.screens.ChartScreen
import com.brandoncano.capacitorcalculator.ui.screens.CeramicCalculatorScreen
import com.brandoncano.capacitorcalculator.ui.screens.HomeScreen

/**
 * Job: Holds all the navigation information and full screen previews
 */

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            HomeScreen(context, navController)
        }
        composable(
            route = Screen.CeramicCalculator.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            val viewModel = viewModel<CeramicCapacitorViewModel>(factory = CapacitorViewModelFactory(context))
            val capacitor = viewModel.getCapacitorLiveData()
            CeramicCalculatorScreen(context, navController, viewModel, capacitor)
        }
        composable(
            route = Screen.About.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            AboutScreen(context)
        }
        composable(
            route = Screen.Chart.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            ChartScreen(context, navController)
        }
    }
}
