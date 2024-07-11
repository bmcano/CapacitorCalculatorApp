package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.CapacitorTolerance
import com.brandoncano.capacitorcalculator.model.CapacitorViewModel
import com.brandoncano.capacitorcalculator.model.TextField
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.components.AppTextButton
import com.brandoncano.capacitorcalculator.ui.components.HomeTextField
import com.brandoncano.capacitorcalculator.ui.components.OutlinedDropDownMenu
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppCard
import com.brandoncano.capacitorcalculator.ui.composeables.AppDivider
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.composeables.ClearMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.composeables.ShareMenuItem
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

/**
 * Job: Holds all the content for the home screen
 */

@Composable
fun HomeScreen(context: Context, navController: NavController, viewModel: CapacitorViewModel) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, viewModel)
        }
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController, viewModel: CapacitorViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    var reloadScreen by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .layoutId(reloadScreen)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuTopAppBar(stringResource(R.string.app_name), interactionSource) {
            ClearMenuItem(interactionSource = interactionSource) {
                viewModel.clearFields()
                reloadScreen++
            }
            ShareMenuItem(viewModel, context, interactionSource)
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }

        AppCard {
            key(reloadScreen) {
                HomeTextField(viewModel, TextField.CODE, viewModel.capacitor.code, R.string.home_text_box_enter_code, KeyboardType.NumberPassword)
                HomeTextField(viewModel, TextField.PF, viewModel.capacitor.pf, R.string.home_text_box_enter_pf)
                HomeTextField(viewModel, TextField.NF, viewModel.capacitor.nf, R.string.home_text_box_enter_nf)
                HomeTextField(viewModel, TextField.UF, viewModel.capacitor.uf, R.string.home_text_box_enter_uf)
                OutlinedDropDownMenu(
                    label = stringResource(id = R.string.home_tolerance_dropdown),
                    items = CapacitorTolerance.entries,
                    viewModel = viewModel,
                    startingValue = viewModel.capacitor.tolerance
                )
            }
            AppDivider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            AppTextButton(text = stringResource(id = R.string.home_button_calculate)) {
                focusManager.clearFocus()
                viewModel.calculateCapacitance()
                reloadScreen++
            }
        }

        ArrowButtonCard(
            Icons.Outlined.FileOpen,
            stringResource(id = R.string.home_view_codes)
        ) {
            navController.navigate(Screen.Chart.route)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomePreview() {
    val app = MainActivity()
    val viewModel = viewModel<CapacitorViewModel>()
    HomeScreen(app, NavController(app), viewModel)
}
