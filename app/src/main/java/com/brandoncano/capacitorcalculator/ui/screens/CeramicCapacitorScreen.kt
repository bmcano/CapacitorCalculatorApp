package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
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
import com.brandoncano.capacitorcalculator.constants.DropdownLists
import com.brandoncano.capacitorcalculator.model.ceramic.CeramicCapacitor
import com.brandoncano.capacitorcalculator.model.ceramic.CeramicCapacitorViewModel
import com.brandoncano.capacitorcalculator.model.CapacitorViewModelFactory
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.components.CapacitorLayout
import com.brandoncano.capacitorcalculator.ui.components.ViewCommonCodeButton
import com.brandoncano.capacitorcalculator.ui.composeables.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.AppDropDownMenu
import com.brandoncano.capacitorcalculator.ui.composeables.AppScreenPreviews
import com.brandoncano.capacitorcalculator.ui.composeables.AppTextField
import com.brandoncano.capacitorcalculator.ui.composeables.ClearSelectionsMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.composeables.MenuTopAppBar
import com.brandoncano.capacitorcalculator.ui.composeables.ShareMenuItem
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleSubhead
import com.brandoncano.capacitorcalculator.util.formatCapacitance
import com.brandoncano.capacitorcalculator.util.formatCode
import com.brandoncano.capacitorcalculator.util.isCapacitanceInvalid
import com.brandoncano.capacitorcalculator.util.isCodeInvalid
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CeramicCalculatorScreen(
    context: Context,
    navController: NavController,
    viewModel: CeramicCapacitorViewModel,
    capacitor: LiveData<CeramicCapacitor>
) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val focusManager = LocalFocusManager.current
            val interactionSource = remember { MutableInteractionSource() }
            var reset by remember { mutableStateOf(false) }
            val onReset: (Boolean) -> Boolean = { resetValue ->
                reset = resetValue
                if (resetValue) {
                    viewModel.clear()
                }
                resetValue
            }
            val pagerState = rememberPagerState(initialPage = 0) { 2 }
            val coroutineScope = rememberCoroutineScope()
            Column {
                MenuTopAppBar(stringResource(R.string.ceramic_calculator_title), interactionSource) {
                    ClearSelectionsMenuItem(interactionSource = interactionSource) {
                        onReset(true)
                        focusManager.clearFocus()
                    }
                    capacitor.value?.let { ShareMenuItem(it, context, interactionSource) }
                    FeedbackMenuItem(context, interactionSource)
                    AboutAppMenuItem(navController, interactionSource)
                }
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Tab(
                        text = {
                            Text(
                                text = stringResource(id = R.string.ceramic_calculator_tab_1),
                                style = textStyleSubhead()
                            )
                        },
                        selected = pagerState.currentPage == 0,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                    )
                    Tab(
                        text = {
                            Text(
                                text = stringResource(id = R.string.ceramic_calculator_tab_2),
                                style = textStyleSubhead()
                            )
                        },
                        selected = pagerState.currentPage == 1,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } }
                    )
                }
                HorizontalPager(state = pagerState) { page ->
                    when (page) {
                        0 -> ContentView1(navController, viewModel, capacitor, reset, onReset)
                        1 -> ContentView2(viewModel, capacitor, reset, onReset)
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentView1(
    navController: NavController,
    viewModel: CeramicCapacitorViewModel,
    capacitorLiveData: LiveData<CeramicCapacitor>,
    reset: Boolean,
    onReset: (Boolean) -> Boolean
) {
    val focusManager = LocalFocusManager.current
    val capacitor by capacitorLiveData.observeAsState(CeramicCapacitor())
    var code by remember { mutableStateOf(capacitor.code) }
    var units by remember { mutableStateOf(capacitor.units) }
    var tolerance by remember { mutableStateOf(capacitor.tolerance) }
    var isError by remember { mutableStateOf(capacitor.isCodeInvalid()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CapacitorLayout(capacitor, isError = isError)
        AppTextField(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            label = stringResource(id = R.string.ceramic_calculator_code),
            text = code,
            reset = reset,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_code),
        ) {
            onReset(false)
            code = it
            viewModel.updateCode(it)
            isError = capacitor.isCodeInvalid()
            if (!isError) {
                viewModel.saveCapacitorValues(capacitor)
                capacitor.formatCapacitance()
            }
        }

        AppDropDownMenu(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            label = R.string.ceramic_calculator_units,
            selectedOption = capacitor.units,
            items = DropdownLists.UNITS,
            reset = reset
        ) {
            units = it
            viewModel.updateUnits(it)
            onReset(false)
            focusManager.clearFocus()
            viewModel.saveCapacitorValues(capacitor)
        }

        AppDropDownMenu(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            label = R.string.ceramic_calculator_tolerance,
            selectedOption = capacitor.tolerance,
            items = DropdownLists.TOLERANCE,
            reset = reset
        ) {
            tolerance = it
            viewModel.updateTolerance(it)
            onReset(false)
            focusManager.clearFocus()
            viewModel.saveCapacitorValues(capacitor)
        }

        ViewCommonCodeButton(navController)
    }
}

@Composable
private fun ContentView2(
    viewModel: CeramicCapacitorViewModel,
    capacitorLiveData: LiveData<CeramicCapacitor>,
    reset: Boolean,
    onReset: (Boolean) -> Boolean
) {
    val focusManager = LocalFocusManager.current
    val capacitor by capacitorLiveData.observeAsState(CeramicCapacitor())
    var capacitance by remember { mutableStateOf(capacitor.capacitance) }
    var units by remember { mutableStateOf(capacitor.units) }
    var isError by remember { mutableStateOf(capacitor.isCapacitanceInvalid()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CapacitorLayout(capacitor, isCode = false, isError = isError)
        AppTextField(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            label = stringResource(id = R.string.ceramic_calculator_capacitance),
            text = capacitance,
            reset = reset,
            isError = isError,
            errorMessage = stringResource(id = R.string.error_invalid_capacitance),
        ) {
            onReset(false)
            capacitance = it
            viewModel.updateCapacitance(it)
            isError = capacitor.isCapacitanceInvalid()
            if (!isError) {
                viewModel.saveCapacitorValues(capacitor)
                capacitor.formatCode()
            }
        }

        AppDropDownMenu(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            label = R.string.ceramic_calculator_units,
            selectedOption = capacitor.units,
            items = DropdownLists.UNITS,
            reset = reset
        ) {
            onReset(false)
            units = it
            viewModel.updateUnits(it)
            focusManager.clearFocus()
            isError = capacitor.isCapacitanceInvalid()
            if (!isError) {
                viewModel.saveCapacitorValues(capacitor)
                capacitor.formatCode()
            }
        }
    }
}

@AppScreenPreviews
@Composable
private fun CeramicCalculatorPreview() {
    val app = MainActivity()
    val viewModel = viewModel<CeramicCapacitorViewModel>(factory = CapacitorViewModelFactory(app))
    val capacitor = MutableLiveData<CeramicCapacitor>()
    CeramicCalculatorScreen(app, NavController(app), viewModel, capacitor)
}