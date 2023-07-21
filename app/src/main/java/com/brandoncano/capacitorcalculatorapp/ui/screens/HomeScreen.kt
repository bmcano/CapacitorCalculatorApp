package com.brandoncano.capacitorcalculatorapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.ui.components.AppTextField
import com.brandoncano.capacitorcalculatorapp.ui.components.BottomShadow
import com.brandoncano.capacitorcalculatorapp.ui.components.HomeAppBar
import com.brandoncano.capacitorcalculatorapp.ui.components.TextTitle
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme

@Composable
fun HomeScreen(context: Context, navController: NavController) {
    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeAppBar(navController)
                BottomShadow(height = 4.dp)
                AppTextField(hint = stringResource(id = R.string.enter_code))
                AppTextField(hint = stringResource(id = R.string.text_box_enter_pf))
                AppTextField(hint = stringResource(id = R.string.text_box_enter_nf))
                AppTextField(hint = stringResource(id = R.string.text_box_enter_uf))
            }
        }
    }
}

