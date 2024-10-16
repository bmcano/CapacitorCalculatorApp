package com.brandoncano.capacitorcalculator.navigation

import android.content.Context
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.capacitorcalculator.constants.Links
import com.brandoncano.capacitorcalculator.model.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.model.capacitorlegacy.CapacitorCapacitorViewModel
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitorViewModel
import com.brandoncano.capacitorcalculator.navigation.calculators.capacitorCodeValuesScreen
import com.brandoncano.capacitorcalculator.ui.screens.capacitorlegacy.CapacitorCalculatorScreen
import com.brandoncano.capacitorcalculator.ui.screens.capacitorvalues.CapacitorValuesScreen
import com.brandoncano.capacitorcalculator.ui.screens.chart.ChartScreen
import com.brandoncano.capacitorcalculator.ui.screens.information.InformationScreen
import com.brandoncano.capacitorcalculator.ui.screens.informationdetails.InformationDetailsScreen
import com.brandoncano.capacitorcalculator.ui.screens.smd.SmdCalculatorScreen
import com.brandoncano.sharedcomponents.data.Apps
import com.brandoncano.sharedcomponents.screen.ViewOurAppsScreen
import com.brandoncano.sharedcomponents.utils.OpenLink

/**
 * Note: Keep each navigation route in alphabetical order
 */

@Composable
fun Navigation(onOpenThemeDialog: () -> Unit) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        aboutScreen(navController)
        capacitorCodeValuesScreen(navController)
        homeScreen(navController, onOpenThemeDialog)
        // TODO migrate these screens
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
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            CapacitorValuesScreen(context, navController)
        }
        composable(
            route = Screen.CommonCodes.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            ChartScreen(context, navController)
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
            route = Screen.Smd.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            val viewModel = viewModel<SmdCapacitorViewModel>(factory = CapacitorViewModelFactory(context))
            val navBarPosition = viewModel.getNavBarSelection()
            val capacitor = viewModel.getCapacitorLiveData()
            SmdCalculatorScreen(context, navController, viewModel, navBarPosition, capacitor)
        }
        // from shared library
        composable(
            route = Screen.ViewOurApps.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            ViewOurAppsScreen(
                context = LocalContext.current,
                app = Apps.Capacitor,
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}

fun navigateToAbout(navController: NavHostController) {
    navController.navigate(Screen.About.route)
}

fun navigateToCapacitorCode(navController: NavHostController) {
    navController.navigate(Screen.CapacitorCalculator.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToSmd(navController: NavHostController) {
    navController.navigate(Screen.Smd.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToCommonCodes(navController: NavHostController) {
    navController.navigate(Screen.CommonCodes.route)
}

fun navigateToCapacitorTypes(navController: NavHostController) {
    navController.navigate(Screen.Information.route)
}

fun navigateToCapacitorValues(navController: NavHostController) {
    navController.navigate(Screen.CapacitorValues.route)
}


fun navigateToOurApps(navController: NavHostController) {
    navController.navigate(Screen.ViewOurApps.route)
}

fun navigateToGooglePlay(context: Context) {
    OpenLink.execute(context, Links.CAPACITOR_PLAYSTORE)
}