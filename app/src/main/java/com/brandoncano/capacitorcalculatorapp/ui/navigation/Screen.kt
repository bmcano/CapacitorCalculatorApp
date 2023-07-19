package com.brandoncano.capacitorcalculatorapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object About : Screen("about screen")
}
