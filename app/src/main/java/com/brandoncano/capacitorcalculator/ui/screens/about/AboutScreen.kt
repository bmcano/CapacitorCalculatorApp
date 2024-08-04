package com.brandoncano.capacitorcalculator.ui.screens.about

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.screens.home.OurAppsButtons
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppTopAppBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

@Composable
fun AboutScreen(context: Context) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ContentView(context)
    }
}

@Composable
private fun ContentView(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        AppTopAppBar(stringResource(R.string.about_title))
        AuthorCard()
        AppInfoCard()
        ViewPrivacyPolicy(context)
        DescriptionCard()
        OurAppsButtons(context)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun AboutPreview() {
    val app = MainActivity()
    CapacitorCalculatorTheme {
        AboutScreen(app)
    }
}
