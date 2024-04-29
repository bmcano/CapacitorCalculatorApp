package com.brandoncano.capacitorcalculator.ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.CapacitorViewModel
import com.brandoncano.capacitorcalculator.model.CapacitorTolerance

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

@Composable
fun errorIcon(isError: Boolean): @Composable (() -> Unit) {
    return { if (isError) Icon(Icons.Outlined.Error, "Error", tint = MaterialTheme.colorScheme.error) }
}

@Composable
fun errorText(isError: Boolean): @Composable (() -> Unit) {
    return { if (isError) TextBody(text = stringResource(id = R.string.error_invalid_input)) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedDropDownMenu(label: String, items: List<CapacitorTolerance>, viewModel: CapacitorViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                expanded = !expanded
            }
        }
    }
    Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
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
                Icon(icon, "", Modifier.clickable { expanded = !expanded })
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
                text = { Text("No Tolerance", style = TextStyle(fontStyle = FontStyle.Italic)) },
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
