package com.brandoncano.capacitorcalculator.navigation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.capacitorcalculator.model.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.model.capacitor.CapacitorCapacitorViewModel
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitorViewModel
import com.brandoncano.capacitorcalculator.ui.screens.about.AboutScreen
import com.brandoncano.capacitorcalculator.ui.screens.capacitor.CapacitorCalculatorScreen
import com.brandoncano.capacitorcalculator.ui.screens.capacitorvalues.CapacitorValuesScreen
import com.brandoncano.capacitorcalculator.ui.screens.chart.ChartScreen
import com.brandoncano.capacitorcalculator.ui.screens.home.HomeScreen
import com.brandoncano.capacitorcalculator.ui.screens.information.InformationScreen
import com.brandoncano.capacitorcalculator.ui.screens.informationdetails.InformationDetailsScreen
import com.brandoncano.capacitorcalculator.ui.screens.smd.SmdCalculatorScreen

/**
 * Note: Keep each navigation route in alphabetical order
 */

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.About.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            AboutScreen(context)
        }
        composable(
            route = Screen.CapacitorCalculator.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            val viewModel = viewModel<CapacitorCapacitorViewModel>(factory = CapacitorViewModelFactory(context))
            val capacitor = viewModel.getCapacitorLiveData()
            CapacitorCalculatorScreen(context, navController, viewModel, capacitor)
        }
        composable(
            route = Screen.CapacitorValues.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            CapacitorValuesScreen(context, navController)
        }
        composable(
            route = Screen.Chart.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            ChartScreen(context, navController)
        }
        composable(
            route = Screen.Home.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            HomeScreen(context, navController)
        }
        composable(
            route = Screen.Information.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            InformationScreen(context, navController)
        }
        composable(
            route = "${Screen.InformationDetails.route}/{arg1}",
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) { backStackEntry ->
            val arg1 = backStackEntry.arguments?.getString("arg1") ?: ""
            val informationDetails = InformationDetails.fromRoute(arg1)
            InformationDetailsScreen(informationDetails)
        }
        composable(
            route = Screen.SmdCalculator.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            val viewModel = viewModel<SmdCapacitorViewModel>(factory = CapacitorViewModelFactory(context))
            val navBarPosition = viewModel.getNavBarSelection()
            val capacitor = viewModel.getCapacitorLiveData()
            SmdCalculatorScreen(context, navController, viewModel, navBarPosition, capacitor)
        }
    }
}
