package com.brandoncano.capacitorcalculator.ui.navigation

import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.MainActivity
import com.brandoncano.capacitorcalculator.ui.screens.AboutScreen
import com.brandoncano.capacitorcalculator.ui.screens.ChartScreen
import com.brandoncano.capacitorcalculator.ui.screens.HomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * Job: Holds all the navigation information and full screen previews
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(context: Context) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(context, navController)
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
            ChartScreen(context)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomePreview() {
    val app = MainActivity()
    HomeScreen(app, NavController(app))
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutPreview() {
    val app = MainActivity()
    AboutScreen(app)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChartPreview() {
    val app = MainActivity()
    ChartScreen(app)
}
