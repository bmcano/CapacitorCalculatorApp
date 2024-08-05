package com.brandoncano.capacitorcalculator.ui.screens.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.TableView
import androidx.compose.material.icons.outlined.WidthFull
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline
import com.brandoncano.capacitorcalculator.ui.theme.textStyleTitle
import com.brandoncano.capacitorcalculator.ui.theme.white
import com.brandoncano.capacitorcalculator.util.OpenLink

@Composable
fun AppIcon() {
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .size(128.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.img_capacitor),
                contentDescription = stringResource(id = R.string.content_description_app_icon),
            )
            Text(
                text = "103",
                style = textStyleTitle().white()
            )
        }
    }
}

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
            stringResource(id = R.string.home_capacitor_calculator_button),
        ) {
            navController.navigate(Screen.CapacitorCalculator.route)
        }
        ArrowButtonCard(
            Icons.Outlined.WidthFull,
            stringResource(id = R.string.home_smd_calculator_button),
        ) {
            navController.navigate(Screen.SmdCalculator.route)
        }
    }
}

@Composable
fun AppInformationScreens(navController: NavController) {
    Column {
        Text(
            text = stringResource(id = R.string.home_capacitors_header_text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            Icons.Outlined.Info,
            stringResource(id = R.string.home_information_button),
        ) {
            navController.navigate(Screen.Information.route)
        }
        ArrowButtonCard(
            Icons.Outlined.TableView,
            stringResource(id = R.string.home_capacitor_values),
        ) {
            navController.navigate(Screen.CapacitorValues.route)
        }
    }
}

@Composable
fun OurAppsButtons(context: Context) {
    Column {
        Text(
            text = stringResource(id = R.string.home_our_apps_header_text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            Icons.Outlined.Grade,
            stringResource(id = R.string.home_rate_this_app),
        ) {
            OpenLink.openCapacitorApp(context)
        }
        ArrowButtonCard(
            listOf(
                // Note: we do this instead because material icons does not have the outlined version
                ImageVector.vectorResource(id = R.drawable.icon_outline_add_to_home_screen),
                ImageVector.vectorResource(id = R.drawable.icon_outline_add_to_home_screen),
            ),
            listOf(
                stringResource(id = R.string.home_view_inductor_app),
                stringResource(id = R.string.home_view_resistor_app)
            ),
            listOf(
                { OpenLink.openInductorApp(context) },
                { OpenLink.openResistorApp(context) }
            ),
        )
    }
}

@AppComponentPreviews
@Composable
private fun AppIconPreview() {
    CapacitorCalculatorTheme {
        AppIcon()
    }
}
