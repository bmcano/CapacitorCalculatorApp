package com.brandoncano.capacitorcalculator.navigation

/**
 * Note: Keep screens in alphabetical order
 */
sealed class Screen(val route: String) {
    data object About : Screen("about_screen")
    data object CapacitorCalculator : Screen("capacitor_calculator_screen")
    data object CapacitorValues : Screen("capacitor_values_screen")
    data object Chart : Screen("chart_screen")
    data object Home : Screen("home_screen")
    data object Information : Screen("information_screen")
    data object InformationDetails : Screen("information_details_screen")
    data object SmdCalculator : Screen("smd_calculator_screen")
}
