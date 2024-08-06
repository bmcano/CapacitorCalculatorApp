package com.brandoncano.capacitorcalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.brandoncano.capacitorcalculator.navigation.Navigation
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapacitorCalculatorTheme {
                Navigation(this)
            }
        }
    }
}
