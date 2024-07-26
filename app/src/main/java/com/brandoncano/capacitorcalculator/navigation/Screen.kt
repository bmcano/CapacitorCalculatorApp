package com.brandoncano.capacitorcalculator.navigation

/**
 * Note: Keep screens in alphabetical order
 */
sealed class Screen(val route: String) {
    data object About : Screen("about_screen")
    data object CeramicCalculator : Screen("ceramic_calculator_screen")
    data object Chart : Screen("chart_screen")
    data object Home : Screen("home_screen")
    data object SmdCalculator : Screen("smd_calculator_screen")
}
