package com.brandoncano.capacitorcalculator.ui.screens.capacitor

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.constants.DropdownLists
import com.brandoncano.capacitorcalculator.model.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.model.capacitor.Capacitor
import com.brandoncano.capacitorcalculator.model.capacitor.CapacitorViewModel
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppDivider
import com.brandoncano.capacitorcalculator.ui.composeables.AppDropDownMenu
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppTextField
import com.brandoncano.capacitorcalculator.ui.composeables.ClearSelectionsMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.composeables.ShareMenuItem
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.isCodeInvalid

@Composable
fun CapacitorCodeScreen(
    context: Context,
    navController: NavController,
    viewModel: CapacitorViewModel,
    capacitor: LiveData<Capacitor>
) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, viewModel, capacitor)
        }
    }
}

@Composable
private fun ContentView(
    context: Context,
    navController: NavController,
    viewModel: CapacitorViewModel,
    capacitorLiveData: LiveData<Capacitor>,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var reset by remember { mutableStateOf(false) }
    val capacitor by capacitorLiveData.observeAsState(Capacitor())
    var code by remember { mutableStateOf(capacitor.code) }
    var units by remember { mutableStateOf(capacitor.units) }
    var tolerance by remember { mutableStateOf(capacitor.tolerance)}
    var voltageRating by remember { mutableStateOf(capacitor.voltageRating)}
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuTopAppBar(stringResource(R.string.capacitor_calculator_title), interactionSource) {
            ClearSelectionsMenuItem(interactionSource) {
                viewModel.clear()
                reset = true
                focusManager.clearFocus()
            }
            ShareMenuItem(capacitor.toString(), context, interactionSource)
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }

        CapacitorValuesText(capacitor, isError)
        AppTextField(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            label = stringResource(id = R.string.capacitor_calculator_code),
            text = code,
            reset = reset,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
        ) {
            reset = false
            code = it
            viewModel.updateCode(code)
            isError = capacitor.isCodeInvalid()
            if (!isError) {
                capacitor.formatCapacitance()
            }
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
            label = R.string.capacitor_calculator_units,
            selectedOption = capacitor.tolerance,
            items = DropdownLists.UNITS,
            reset = reset
        ) {
            reset = false
            units = it
            viewModel.updateUnits(it)
            focusManager.clearFocus()
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
            label = R.string.capacitor_calculator_tolerance,
            selectedOption = capacitor.tolerance,
            items = DropdownLists.TOLERANCE,
            reset = reset
        ) {
            reset = false
            tolerance = it
            viewModel.updateTolerance(it)
            focusManager.clearFocus()
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
            label = R.string.capacitor_calculator_voltage_rating,
            selectedOption = capacitor.voltageRating,
            items = DropdownLists.VOLTAGE_RATING,
            reset = reset
        ) {
            reset = false
            voltageRating = it
            viewModel.updateVoltageRating(it)
            focusManager.clearFocus()
        }
        AppDivider(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            onCard = false
        )
        CapacitorInformation()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@AppScreenPreviews
@Composable
private fun CapacitorCodePreview() {
    CapacitorCalculatorTheme {
        val app = MainActivity()
        val viewModel = viewModel<CapacitorViewModel>(factory = CapacitorViewModelFactory(app))
        val capacitor = viewModel.getCapacitorLiveData()
        CapacitorCodeScreen(app, NavController(app), viewModel, capacitor)
    }
}
