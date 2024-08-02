package com.brandoncano.capacitorcalculator.ui.screens.capacitor

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.brandoncano.capacitorcalculator.components.Tolerance
import com.brandoncano.capacitorcalculator.constants.DropdownLists
import com.brandoncano.capacitorcalculator.model.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.model.capacitor.Capacitor
import com.brandoncano.capacitorcalculator.model.capacitor.CapacitorCapacitorViewModel
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppDropDownMenu
import com.brandoncano.capacitorcalculator.ui.composeables.AppMenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppTextField
import com.brandoncano.capacitorcalculator.ui.composeables.ClearSelectionsMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.ShareMenuItem
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.formatCode
import com.brandoncano.capacitorcalculator.util.isCapacitanceInvalid
import com.brandoncano.capacitorcalculator.util.isCodeInvalid
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class) // rememberPagerState()
@Composable
fun CapacitorCalculatorScreen(
    context: Context,
    navController: NavController,
    viewModel: CapacitorCapacitorViewModel,
    capacitor: LiveData<Capacitor>
) {
    CapacitorCalculatorTheme {
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
                    ShareMenuItem(capacitor.value?.toString() ?: "", context, showMenu)
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
                        0 -> ContentView1(navController, viewModel, capacitor, reset, onReset)
                        1 -> ContentView2(navController, viewModel, capacitor, reset, onReset)
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentView1(
    navController: NavController,
    viewModel: CapacitorCapacitorViewModel,
    capacitorLiveData: LiveData<Capacitor>,
    reset: Boolean,
    onReset: (Boolean) -> Boolean,
) {
    val focusManager = LocalFocusManager.current
    val capacitor by capacitorLiveData.observeAsState(Capacitor())
    var code by remember { mutableStateOf(capacitor.code) }
    var units by remember { mutableStateOf(capacitor.units) }
    var tolerance by remember { mutableStateOf(capacitor.tolerance) }
    var voltageRating by remember { mutableStateOf(capacitor.voltageRating) }
    var isError by remember { mutableStateOf(capacitor.isCodeInvalid()) }
    capacitor.isCapacitanceToCode = false

    fun onValueChanged(c: String, u: String, t: String, v: String) {
        onReset(false)
        code = c
        units = u
        tolerance = t
        voltageRating = v
        viewModel.updateCode(c)
        viewModel.updateUnits(u)
        viewModel.updateTolerance(t)
        viewModel.updateVoltageRating(v)
        isError = capacitor.isCodeInvalid()
        if (!isError) {
            viewModel.saveCapacitorValues(capacitor)
            capacitor.formatCapacitance()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CapacitanceText(capacitor, isError, !capacitor.isCapacitanceToCode)
        AppTextField(
            modifier = Modifier.padding(top = 24.dp),
            label = stringResource(id = R.string.capacitor_calculator_code),
            text = code,
            reset = reset,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
        ) {
            onValueChanged(it, units, tolerance, voltageRating)
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.capacitor_calculator_units,
            selectedOption = capacitor.units,
            items = DropdownLists.UNITS,
            reset = reset
        ) {
            focusManager.clearFocus()
            onValueChanged(code, it, tolerance, voltageRating)
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.capacitor_calculator_tolerance,
            selectedOption = capacitor.tolerance,
            items = DropdownLists.TOLERANCE,
            reset = reset
        ) {
            focusManager.clearFocus()
            onValueChanged(code, units, it, voltageRating)
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.capacitor_calculator_voltage_rating,
            selectedOption = capacitor.voltageRating,
            items = DropdownLists.VOLTAGE_RATING,
            reset = reset
        ) {
            focusManager.clearFocus()
            onValueChanged(code, units, tolerance, it)
        }
        CapacitorInformation()
        ViewCommonCodeButton(navController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ContentView2(
    navController: NavController,
    viewModel: CapacitorCapacitorViewModel,
    capacitorLiveData: LiveData<Capacitor>,
    reset: Boolean,
    onReset: (Boolean) -> Boolean,
) {
    val focusManager = LocalFocusManager.current
    val capacitor by capacitorLiveData.observeAsState(Capacitor())
    var capacitance by remember { mutableStateOf(capacitor.capacitance) }
    var units by remember { mutableStateOf(capacitor.units) }
    var tolerance by remember { mutableStateOf(capacitor.tolerance) }
    var isError by remember { mutableStateOf(capacitor.isCapacitanceInvalid()) }
    capacitor.isCapacitanceToCode = true

    fun onValueChanged(c: String, u: String, t: String) {
        onReset(false)
        capacitance = c
        units = u
        tolerance = t
        viewModel.updateCapacitance(c)
        viewModel.updateUnits(u)
        viewModel.updateTolerance(t)
        isError = capacitor.isCapacitanceInvalid()
        if (!isError) {
            viewModel.saveCapacitorValues(capacitor)
            capacitor.formatCode()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CapacitanceText(capacitor, isError, !capacitor.isCapacitanceToCode)
        AppTextField(
            modifier = Modifier.padding(top = 24.dp),
            label = stringResource(id = R.string.capacitor_calculator_capacitance),
            text = capacitance,
            reset = reset,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_capacitance),
        ) {
            onValueChanged(it, units, tolerance)
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.capacitor_calculator_units,
            selectedOption = capacitor.units,
            items = DropdownLists.UNITS,
            reset = reset
        ) {
            focusManager.clearFocus()
            onValueChanged(capacitance, it, tolerance)
        }
        AppDropDownMenu(
            modifier = Modifier.padding(top = 12.dp),
            label = R.string.capacitor_calculator_tolerance,
            selectedOption = Tolerance.getToleranceValue(capacitor.tolerance),
            items = DropdownLists.TOLERANCE_PERCENTAGE,
            reset = reset
        ) {
            focusManager.clearFocus()
            val t = Tolerance.getLetterValue(it)
            onValueChanged(capacitance, units, t)
        }
        CapacitorInformation()
        ViewCommonCodeButton(navController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@AppScreenPreviews
@Composable
private fun CapacitorCalculatorPreview() {
    val app = MainActivity()
    val viewModel = viewModel<CapacitorCapacitorViewModel>(factory = CapacitorViewModelFactory(app))
    val capacitor = MutableLiveData<Capacitor>()
    CapacitorCalculatorScreen(app, NavController(app), viewModel, capacitor)
}
