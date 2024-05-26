package com.brandoncano.capacitorcalculator.ui.composeables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.CapacitorTolerance
import com.brandoncano.capacitorcalculator.model.CapacitorViewModel
import com.brandoncano.capacitorcalculator.model.TextField

/**
 * Job: Components specific to the home screen
 */

@Composable
fun HomeTextField(
    viewModel: CapacitorViewModel,
    textField: TextField,
    startingValue: String,
    stringResId: Int,
    keyboardType: KeyboardType = KeyboardType.Number
) {
    var value by remember { mutableStateOf(startingValue) }
    val topPadding = if (textField == TextField.CODE) 8.dp else 0.dp
    val outlinedTextFieldModifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = topPadding)
        .fillMaxWidth()
    OutlinedTextField(
        modifier = outlinedTextFieldModifier,
        value = value,
        onValueChange = { newValue ->
            value = newValue
            viewModel.textField = textField
            viewModel.capacitor = when (textField) {
                TextField.CODE -> viewModel.capacitor.copy(code = newValue)
                TextField.NF -> viewModel.capacitor.copy(nf = newValue)
                TextField.PF -> viewModel.capacitor.copy(pf = newValue)
                TextField.UF -> viewModel.capacitor.copy(uf = newValue)
            }
        },
        textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
        label = { Text(stringResource(id = stringResId)) },
        trailingIcon = {
            if (viewModel.isError && viewModel.textField == textField) {
                Icon(
                    imageVector = Icons.Outlined.Error,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        supportingText = {
            if (viewModel.isError && viewModel.textField == textField) {
                TextBody(text = stringResource(R.string.error_invalid_input))
            }
        },
        isError = viewModel.isError,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,
    )
}

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
    Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
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
                    selectedText = ""
                    expanded = false
                },
            )
            items.forEach {
                val toleranceText: String = it.toString()
                DropdownMenuItem(
                    text = { Text(toleranceText) },
                    onClick = {
                        viewModel.capacitor.tolerance = it
                        selectedText = toleranceText
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
fun AppTextButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp,
            disabledElevation = 0.dp
        )
    ) {
        TextLabel(text = text)
    }
}
