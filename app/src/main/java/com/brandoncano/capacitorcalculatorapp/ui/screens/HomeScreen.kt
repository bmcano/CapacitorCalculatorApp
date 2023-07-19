package com.brandoncano.capacitorcalculatorapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculatorapp.ui.components.OutlinedTextField
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme

@Composable
fun HomeScreen(context: Context, navController: NavController) {
    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(hint = "Code")
                OutlinedTextField(hint = "pF")
                OutlinedTextField(hint = "nF")
                OutlinedTextField(hint = "uF")
            }
        }
    }
}