package com.brandoncano.capacitorcalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline

/**
 * Job: Components specific to the home screen
 */

@Composable
fun AppCalculatorButtons(navController: NavController) {
    Column {
        Text(
            text = stringResource(id = R.string.home_calculators_header_text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            Icons.Outlined.Calculate,
            stringResource(id = R.string.home_ceramic_calculator_button),
        ) {
            navController.navigate(Screen.CeramicCalculator.route)
        }
    }
}


