package com.brandoncano.capacitorcalculator.navigation

/**
 * Job: Holds the route for each different screen
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object CeramicCalculator : Screen("ceramic_calculator_screen")
    data object About : Screen("about_screen")
    data object Chart : Screen("chart_screen")
}
