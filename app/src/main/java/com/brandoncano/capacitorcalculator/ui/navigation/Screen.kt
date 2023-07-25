package com.brandoncano.capacitorcalculator.ui.navigation

/**
 * Job: Holds the route for each different screen
 */
sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object About : Screen("about_screen")
    object Chart : Screen("chart_screen")
}
