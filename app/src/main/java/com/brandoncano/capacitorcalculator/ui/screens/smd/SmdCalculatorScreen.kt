package com.brandoncano.capacitorcalculator.ui.screens.smd

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.constants.DropdownLists
import com.brandoncano.capacitorcalculator.model.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitor
import com.brandoncano.capacitorcalculator.model.smd.SmdCapacitorViewModel
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppDropDownMenu
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppTextField
import com.brandoncano.capacitorcalculator.ui.composeables.ClearSelectionsMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.composeables.ShareMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.SmdNavigationBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.isSmdInputInvalid

@Composable
fun SmdCalculatorScreen(
    context: Context,
    navController: NavController,
    viewModel: SmdCapacitorViewModel,
    navBarPosition: Int,
    capacitor: LiveData<SmdCapacitor>
) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, viewModel, navBarPosition, capacitor)
        }
    }
}

@Composable
private fun ContentView(
    context: Context,
    navController: NavController,
    viewModel: SmdCapacitorViewModel,
    navBarPosition: Int,
    capacitorLiveData: LiveData<SmdCapacitor>,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    var reset by remember { mutableStateOf(false) }
    val capacitor by capacitorLiveData.observeAsState(SmdCapacitor())
    var code by remember { mutableStateOf(capacitor.code) }
    var units by remember { mutableStateOf(capacitor.units) }
    var isError by remember { mutableStateOf(capacitor.isSmdInputInvalid()) }

    Scaffold(
        bottomBar = {
            SmdNavigationBar(navBarSelection) {
                navBarSelection = it
                viewModel.saveNavBarSelection(it)
                isError = capacitor.isSmdInputInvalid()
                if (!isError) {
                    viewModel.saveCapacitorValues(capacitor)
                    capacitor.formatCapacitance()
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuTopAppBar(stringResource(R.string.smd_calculator_title), interactionSource) {
                ClearSelectionsMenuItem(interactionSource) {
                    viewModel.clear()
                    reset = true
                    focusManager.clearFocus()
                }
                ShareMenuItem(capacitor.toString(), context, interactionSource)
                FeedbackMenuItem(context, interactionSource)
                AboutAppMenuItem(navController, interactionSource)
            }
            SmdCapacitorLayout(capacitor)
            AppTextField(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
                label = stringResource(id = R.string.smd_calculator_code_hint),
                text = code,
                reset = reset,
                isError = isError,
                errorMessage = stringResource(id = R.string.error_invalid_code),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            ) {
                reset = false
                code = it
                viewModel.updateCode(code)
                isError = capacitor.isSmdInputInvalid()
                if (!isError) {
                    viewModel.saveCapacitorValues(capacitor)
                    capacitor.formatCapacitance()
                }
            }
            AppDropDownMenu(
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
                label = R.string.smd_calculator_units_hint,
                selectedOption = capacitor.units,
                items = DropdownLists.UNITS,
                reset = reset,
            ) {
                units = it
                viewModel.updateUnits(it)
                reset = false
                focusManager.clearFocus()
                viewModel.saveCapacitorValues(capacitor)
            }
        }
    }
}

@AppScreenPreviews
@Composable
private fun SmdCalculatorPreview() {
    val app = MainActivity()
    val viewModel = viewModel<SmdCapacitorViewModel>(factory = CapacitorViewModelFactory(app))
    val capacitor = MutableLiveData<SmdCapacitor>()
    SmdCalculatorScreen(app, NavController(app), viewModel, 0, capacitor)
}
