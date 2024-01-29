package com.brandoncano.capacitorcalculator.ui.navigation

/**
 * Job: Holds the route for each different screen
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object About : Screen("about_screen")
    data object Chart : Screen("chart_screen")
}
