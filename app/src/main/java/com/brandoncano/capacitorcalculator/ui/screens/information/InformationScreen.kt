package com.brandoncano.capacitorcalculator.ui.screens.information

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.components.InformationDetails
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppDivider
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.gray
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.ui.theme.textStyleCallout
import com.brandoncano.capacitorcalculator.ui.theme.textStyleCaption

@Composable
fun InformationScreen(context: Context, navController: NavController) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController)
        }
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        MenuTopAppBar(titleText = stringResource(id = R.string.information_title), interactionSource) {
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }
        Text(
            text = stringResource(id = R.string.information_header_text),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleBody().gray(),
        )
        AppDivider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            onCard = false
        )
        // Note: will leave items commented out until their page is added
        ArrowButtonCardWithSubText(
            cardTexts = listOf(
                stringResource(id = R.string.information_ceramic_header),
                stringResource(id = R.string.information_film_header),
                stringResource(id = R.string.information_electrolytic_header),
                stringResource(id = R.string.information_super_header),
                stringResource(id = R.string.information_mica_header),
//                stringResource(id = R.string.information_polymer_header),
//                stringResource(id = R.string.information_paper_header),
//                stringResource(id = R.string.information_glass_header),
//                stringResource(id = R.string.information_silicon_header),
//                stringResource(id = R.string.information_variable_header),
//                stringResource(id = R.string.information_hybrid_header),
            ),
            subTexts = listOf(
                emptyList(),
                listOf(
                    stringResource(id = R.string.information_film_subtext_1),
                    stringResource(id = R.string.information_film_subtext_2),
                    stringResource(id = R.string.information_film_subtext_3),
                    stringResource(id = R.string.information_film_subtext_4),
                    stringResource(id = R.string.information_film_subtext_5),
                    stringResource(id = R.string.information_film_subtext_6),
                    stringResource(id = R.string.information_film_subtext_7),
                ),
                listOf(
                    stringResource(id = R.string.information_electrolytic_subtext_1),
                    stringResource(id = R.string.information_electrolytic_subtext_2),
                    stringResource(id = R.string.information_electrolytic_subtext_3)
                ),
                emptyList(),
                emptyList(),
//                listOf(
//                    "Conductive polymer aluminum solid capacitor (OS-CON)",
//                    "Conductive polymer tantalum solid capacitor"
//                ),
//                emptyList(),
//                emptyList(),
//                emptyList(),
//                listOf(
//                    "Trimmer capacitor",
//                    "Tuning capacitor"
//                ),
//                emptyList()
            ),
            onClicks = listOf(
                { navController.navigate("${Screen.InformationDetails.route}/${InformationDetails.Ceramic.route}") },
                { navController.navigate("${Screen.InformationDetails.route}/${InformationDetails.Film.route}") },
                { navController.navigate("${Screen.InformationDetails.route}/${InformationDetails.Electrolytic.route}") },
                { navController.navigate("${Screen.InformationDetails.route}/${InformationDetails.SuperCapacitor.route}") },
                { navController.navigate("${Screen.InformationDetails.route}/${InformationDetails.Mica.route}") },
//                { /* TODO("add this page") */ },
//                { /* TODO("add this page") */ },
//                { /* TODO("add this page") */ },
//                { /* TODO("add this page") */ },
//                { /* TODO("add this page") */ },
//                { /* TODO("add this page") */ },
            )
        )
        AppDivider(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp))
        Text(
            text = stringResource(id = R.string.information_footer_text),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            style = textStyleCaption().gray(),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@AppScreenPreviews
@Composable
private fun InformationScreenPreview() {
    val app = MainActivity()
    InformationScreen(app, NavController(app))
}
