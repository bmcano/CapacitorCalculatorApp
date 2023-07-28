package com.brandoncano.capacitorcalculator.ui.navigation

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.capacitorcalculator.MainActivity
import com.brandoncano.capacitorcalculator.ui.screens.AboutScreen
import com.brandoncano.capacitorcalculator.ui.screens.ChartScreen
import com.brandoncano.capacitorcalculator.ui.screens.HomeScreen

/**
 * Job: Holds all the navigation information and full screen previews
 */

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(context, navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(context)
        }
        composable(route = Screen.Chart.route) {
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