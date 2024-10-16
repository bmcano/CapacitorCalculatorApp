package com.brandoncano.capacitorcalculator.ui.screens.capacitorlegacy

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.data.Tolerance
import com.brandoncano.capacitorcalculator.constants.DropdownLists
import com.brandoncano.capacitorcalculator.model.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.model.capacitorlegacy.CapacitorLegacy
import com.brandoncano.capacitorcalculator.model.capacitorlegacy.CapacitorCapacitorViewModel
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.capacitorcalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composables.ShareMenuItem
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.formatCode
import com.brandoncano.capacitorcalculator.util.isCapacitanceInvalid
import com.brandoncano.capacitorcalculator.util.isCodeInvalid
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import kotlinx.coroutines.launch

@Composable
fun CapacitorCalculatorScreen(
    context: Context,
    navController: NavController,
    viewModel: CapacitorCapacitorViewModel,
    capacitorLegacy: LiveData<CapacitorLegacy>
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val focusManager = LocalFocusManager.current
        val interactionSource = remember { MutableInteractionSource() }
        val showMenu = remember { mutableStateOf(false) }
        val pagerState = rememberPagerState(initialPage = 0) { 2 }
        val coroutineScope = rememberCoroutineScope()
        var reset by remember { mutableStateOf(false) }
        val onReset: (Boolean) -> Boolean = { resetValue ->
            reset = resetValue
            if (resetValue) {
                viewModel.clear()
            }
            resetValue
        }

        Column {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.capacitor_calculator_title),
                interactionSource = interactionSource,
                showMenu = showMenu,
            ) {
                ClearSelectionsMenuItem {
                    showMenu.value = false
                    onReset(true)
                    focusManager.clearFocus()
                }
                ShareMenuItem(capacitorLegacy.value?.toString() ?: "", context, showMenu)
                FeedbackMenuItem(context, showMenu)
                AboutAppMenuItem(navController, showMenu)
            }
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    text = { TabText(title = R.string.capacitor_calculator_tab_1) },
                    selected = pagerState.currentPage == 0,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                )
                Tab(
                    text = { TabText(title = R.string.capacitor_calculator_tab_2) },
                    selected = pagerState.currentPage == 1,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } }
                )
            }
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> ContentView1(viewModel, capacitorLegacy, reset, onReset)
                    1 -> ContentView2(viewModel, capacitorLegacy, reset, onReset)
                }
            }
        }
    }
}

@Composable
private fun ContentView1(
    viewModel: CapacitorCapacitorViewModel,
    capacitorLegacyLiveData: LiveData<CapacitorLegacy>,
    reset: Boolean,
    onReset: (Boolean) -> Boolean,
) {
    val focusManager = LocalFocusManager.current
    val capacitorLegacy by capacitorLegacyLiveData.observeAsState(CapacitorLegacy())
    var code = remember { mutableStateOf(capacitorLegacy.code) }
    var units by remember { mutableStateOf(capacitorLegacy.units) }
    var tolerance by remember { mutableStateOf(capacitorLegacy.tolerance) }
    var voltageRating by remember { mutableStateOf(capacitorLegacy.voltageRating) }
    var isError by remember { mutableStateOf(capacitorLegacy.isCodeInvalid()) }
    capacitorLegacy.isCapacitanceToCode = false

    fun postSelectionActions() {
        onReset(false)
        viewModel.updateValues(code.value, units, tolerance, voltageRating)
        isError = capacitorLegacy.isCodeInvalid()
        if (!isError) {
            viewModel.saveCapacitorValues(capacitorLegacy)
            capacitorLegacy.formatCapacitance()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CapacitanceText(capacitorLegacy, isError, !capacitorLegacy.isCapacitanceToCode)
        AppTextField(
            modifier = Modifier.padding(top = 24.dp),
            label = stringResource(id = R.string.capacitor_calculator_code),
            value = code,
            reset = reset,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
        ) {
            code.value = it
            postSelectionActions()
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.capacitor_calculator_units),
            selectedOption = capacitorLegacy.units,
            items = DropdownLists.UNITS,
            reset = reset
        ) {
            units = it
            focusManager.clearFocus()
            postSelectionActions()
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.capacitor_calculator_tolerance),
            selectedOption = capacitorLegacy.tolerance,
            items = DropdownLists.TOLERANCE,
            reset = reset
        ) {
            tolerance = it
            focusManager.clearFocus()
            postSelectionActions()
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.capacitor_calculator_voltage_rating),
            selectedOption = capacitorLegacy.voltageRating,
            items = DropdownLists.VOLTAGE_RATING,
            reset = reset
        ) {
            voltageRating = it
            focusManager.clearFocus()
            postSelectionActions()
        }
        CapacitorInformation()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ContentView2(
    viewModel: CapacitorCapacitorViewModel,
    capacitorLegacyLiveData: LiveData<CapacitorLegacy>,
    reset: Boolean,
    onReset: (Boolean) -> Boolean,
) {
    val focusManager = LocalFocusManager.current
    val capacitorLegacy by capacitorLegacyLiveData.observeAsState(CapacitorLegacy())
    var capacitance = remember { mutableStateOf(capacitorLegacy.capacitance) }
    var units by remember { mutableStateOf(capacitorLegacy.units) }
    var tolerance by remember { mutableStateOf(capacitorLegacy.tolerance) }
    var isError by remember { mutableStateOf(capacitorLegacy.isCapacitanceInvalid()) }
    capacitorLegacy.isCapacitanceToCode = true

    fun postSelectionActions() {
        onReset(false)
        viewModel.updateValues(capacitance.value, units, tolerance)
        isError = capacitorLegacy.isCapacitanceInvalid()
        if (!isError) {
            viewModel.saveCapacitorValues(capacitorLegacy)
            capacitorLegacy.formatCode()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CapacitanceText(capacitorLegacy, isError, !capacitorLegacy.isCapacitanceToCode)
        AppTextField(
            modifier = Modifier.padding(top = 24.dp),
            label = stringResource(id = R.string.capacitor_calculator_capacitance),
            value = capacitance,
            reset = reset,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_capacitance),
        ) {
            capacitance.value = it
            postSelectionActions()
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.capacitor_calculator_units),
            selectedOption = capacitorLegacy.units,
            items = DropdownLists.UNITS,
            reset = reset
        ) {
            units = it
            focusManager.clearFocus()
            postSelectionActions()
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = stringResource(R.string.capacitor_calculator_tolerance),
            selectedOption = Tolerance.getToleranceValue(capacitorLegacy.tolerance),
            items = DropdownLists.TOLERANCE_PERCENTAGE,
            reset = reset
        ) {
            tolerance = Tolerance.getLetterValue(it)
            focusManager.clearFocus()
            postSelectionActions()
        }
        CapacitorInformation()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun CapacitorCalculatorPreview() {
    val app = MainActivity()
    val viewModel = viewModel<CapacitorCapacitorViewModel>(factory = CapacitorViewModelFactory(app))
    val capacitorLegacy = MutableLiveData<CapacitorLegacy>()
    CapacitorCalculatorTheme {
        CapacitorCalculatorScreen(app, NavController(app), viewModel, capacitorLegacy)
    }
}
