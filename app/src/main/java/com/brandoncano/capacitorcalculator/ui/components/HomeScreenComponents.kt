package com.brandoncano.capacitorcalculator.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.model.CapacitorViewModel
import com.brandoncano.capacitorcalculator.model.FieldValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTextField(
    viewModel: CapacitorViewModel,
    fieldValues: FieldValues,
    startingValue: String,
    stringResId: Int,
) {
    var value by remember { mutableStateOf(startingValue) }
    val outlinedTextFieldModifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        .fillMaxWidth()

    OutlinedTextField(
        modifier = outlinedTextFieldModifier,
        value = value,
        onValueChange = { newValue ->
            value = newValue
            viewModel.fieldValues = fieldValues
            viewModel.capacitor = when (fieldValues) {
                FieldValues.Code -> viewModel.capacitor.copy(code = newValue)
                FieldValues.NF -> viewModel.capacitor.copy(nf = newValue)
                FieldValues.PF -> viewModel.capacitor.copy(pf = newValue)
                FieldValues.UF -> viewModel.capacitor.copy(uf = newValue)
            }
        },
        textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
        label = { Text(stringResource(id = stringResId)) },
        trailingIcon = errorIcon(viewModel.isError && viewModel.fieldValues == fieldValues),
        supportingText = errorText(viewModel.isError && viewModel.fieldValues == fieldValues),
        isError = viewModel.isError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        singleLine = true,
        maxLines = 1,
    )
}