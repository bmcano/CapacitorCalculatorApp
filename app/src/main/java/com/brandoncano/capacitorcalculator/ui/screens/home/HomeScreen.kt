package com.brandoncano.capacitorcalculator.ui.screens.home

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.constants.Links
import com.brandoncano.capacitorcalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composables.AppThemeMenuItem
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleHeadline

@Composable
fun HomeScreen(
    openMenu: MutableState<Boolean>,
    onOpenThemeDialog: () -> Unit,
    onAboutTapped: () -> Unit,
    onCapacitorCodeTapped: () -> Unit,
    onSmdTapped: () -> Unit,
    onCapacitorTypesTapped: () -> Unit,
    onCapacitorValuesTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        HomeScreenContent(
            openMenu = openMenu,
            onOpenThemeDialog = onOpenThemeDialog,
            onAboutTapped = onAboutTapped,
            onCapacitorCodeTapped = onCapacitorCodeTapped,
            onSmdTapped = onSmdTapped,
            onCapacitorTypesTapped = onCapacitorTypesTapped,
            onCapacitorValuesTapped = onCapacitorValuesTapped,
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
        )
    }
}

@Composable
private fun HomeScreenContent(
    openMenu: MutableState<Boolean>,
    onOpenThemeDialog: () -> Unit,
    onAboutTapped: () -> Unit,
    onCapacitorCodeTapped: () -> Unit,
    onSmdTapped: () -> Unit,
    onCapacitorTypesTapped: () -> Unit,
    onCapacitorValuesTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppMenuTopAppBar(
            titleText = stringResource(R.string.app_name),
            interactionSource = remember { MutableInteractionSource() },
            showMenu = openMenu,
            navigationIcon = null,
            onNavigateBack = {},
        ) {
            FeedbackMenuItem(Links.APP_NAME, openMenu)
            AppThemeMenuItem(openMenu, onOpenThemeDialog)
            AboutAppMenuItem(onAboutTapped)
        }

        AppIcon()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.home_calculators_header_text),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Code,
                text = stringResource(id = R.string.home_capacitor_calculator_button),
                onClick = onCapacitorCodeTapped
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Memory,
                text = stringResource(id = R.string.home_smd_calculator_button),
                onClick = onSmdTapped
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        CapacitorInformationButtons(
            onCapacitorTypesTapped = onCapacitorTypesTapped,
            onCapacitorValuesTapped = onCapacitorValuesTapped,
        )
        Spacer(modifier = Modifier.height(32.dp))
        OurAppsButtons(
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@AppScreenPreviews
@Composable
private fun HomePreview() {
    CapacitorCalculatorTheme {
        HomeScreen(
            openMenu = remember { mutableStateOf(false) },
            onOpenThemeDialog = {},
            onAboutTapped = {},
            onCapacitorCodeTapped = {},
            onSmdTapped = {},
            onCapacitorTypesTapped = {},
            onCapacitorValuesTapped = {},
            onRateThisAppTapped = {},
            onViewOurAppsTapped = {},
        )
    }
}
