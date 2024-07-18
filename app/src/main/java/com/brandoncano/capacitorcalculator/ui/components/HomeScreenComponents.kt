package com.brandoncano.capacitorcalculator.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.components.CapacitorTolerance
import com.brandoncano.capacitorcalculator.model.CapacitorViewModel
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline

/**
 * Job: Components specific to the home screen
 */



@Composable
fun OutlinedDropDownMenu(
    label: String,
    items: List<CapacitorTolerance>,
    viewModel: CapacitorViewModel,
    startingValue: CapacitorTolerance?
) {
    val interactionSource = remember { MutableInteractionSource() }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("${startingValue ?: ""}") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                expanded = !expanded
            }
        }
    }
    Column(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        OutlinedTextField(
            value = selectedText,
            readOnly = true,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() }
                .clickable(interactionSource, null, enabled = true) { expanded = !expanded },
            label = { Text(label) },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            interactionSource = interactionSource
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .clickable(interactionSource, null, enabled = true) { expanded = !expanded }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = R.string.home_no_tolerance),
                        style = TextStyle(fontStyle = FontStyle.Italic)
                    )
                },
                onClick = {
                    viewModel.updateTolerance(null)
                    selectedText = ""
                    expanded = false
                },
            )
            items.forEach {
                val toleranceText: String = it.toString()
                DropdownMenuItem(
                    text = { Text(toleranceText) },
                    onClick = {
                        viewModel.updateTolerance(it)
                        selectedText = toleranceText
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
fun CodeValueCalculatorButton(navController: NavController) {
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
            stringResource(id = R.string.home_code_value_button),
        ) {
            navController.navigate(Screen.CodeValue.route)
        }
    }
}

@Composable
fun ViewCommonCodeButton(navController: NavController) {
    ArrowButtonCard(
        Icons.Outlined.FileOpen,
        stringResource(id = R.string.home_view_codes)
    ) {
        navController.navigate(Screen.Chart.route)
    }
}
