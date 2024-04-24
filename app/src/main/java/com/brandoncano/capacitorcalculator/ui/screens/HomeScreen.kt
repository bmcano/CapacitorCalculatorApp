package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.Capacitor
import com.brandoncano.capacitorcalculator.model.FieldValues
import com.brandoncano.capacitorcalculator.model.Tolerance
import com.brandoncano.capacitorcalculator.ui.components.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.components.AppDivider
import com.brandoncano.capacitorcalculator.ui.components.AppTextButton
import com.brandoncano.capacitorcalculator.ui.components.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.components.ClearMenuItem
import com.brandoncano.capacitorcalculator.ui.components.DefaultCard
import com.brandoncano.capacitorcalculator.ui.components.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.components.MenuAppBar
import com.brandoncano.capacitorcalculator.ui.components.OutlinedDropDownMenu
import com.brandoncano.capacitorcalculator.ui.components.ShareMenuItem
import com.brandoncano.capacitorcalculator.ui.components.TextBody
import com.brandoncano.capacitorcalculator.ui.components.TextLabel
import com.brandoncano.capacitorcalculator.ui.components.errorIcon
import com.brandoncano.capacitorcalculator.ui.components.errorText
import com.brandoncano.capacitorcalculator.ui.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.util.CapacitorValues

/**
 * Job: Holds all the content for the home screen
 */

@Composable
fun HomeScreen(context: Context, navController: NavController) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) { Content(context, navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(context: Context, navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    var fieldValues: FieldValues by remember { mutableStateOf(FieldValues.Code) }
    var isError by remember { mutableStateOf(false) }
    var tolerance by remember { mutableStateOf("") }
    var reloadId by remember { mutableStateOf(0) }
    var capacitor by remember { mutableStateOf(Capacitor()) }

    val focusManager = LocalFocusManager.current
    val outlinedTextFieldModifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        .fillMaxWidth()

    Column(
        modifier = Modifier
            .layoutId(reloadId)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuAppBar(stringResource(R.string.app_name), interactionSource) {
            ClearMenuItem(interactionSource = interactionSource) {
                capacitor.clear()
                tolerance = ""
                isError = false
                reloadId++
            }
            ShareMenuItem(capacitor, context, interactionSource)
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }

        DefaultCard {
            // TODO - make these text fields properly reusable
            OutlinedTextField(
                modifier = outlinedTextFieldModifier,
                value = capacitor.code,
                onValueChange = {
                    capacitor = capacitor.copy(code = it)
                    fieldValues = FieldValues.Code
                },
                textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
                label = { Text(stringResource(id = R.string.home_text_box_enter_code)) },
                trailingIcon = errorIcon(isError && fieldValues == FieldValues.Code),
                supportingText = errorText(isError && fieldValues == FieldValues.Code),
                isError = isError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                singleLine = true,
                maxLines = 1,
            )
            OutlinedTextField(
                modifier = outlinedTextFieldModifier,
                value = capacitor.pf,
                onValueChange = {
                    capacitor = capacitor.copy(pf = it)
                    fieldValues = FieldValues.PF
                },
                textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
                label = { Text(stringResource(id = R.string.home_text_box_enter_pf)) },
                trailingIcon = errorIcon(isError && fieldValues == FieldValues.PF),
                supportingText = errorText(isError && fieldValues == FieldValues.PF),
                isError = isError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                maxLines = 1,
            )
            OutlinedTextField(
                modifier = outlinedTextFieldModifier,
                value = capacitor.nf,
                onValueChange = {
                    capacitor = capacitor.copy(nf = it)
                    fieldValues = FieldValues.NF
                },
                textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
                label = { Text(stringResource(id = R.string.home_text_box_enter_nf)) },
                trailingIcon = errorIcon(isError && fieldValues == FieldValues.NF),
                supportingText = errorText(isError && fieldValues == FieldValues.NF),
                isError = isError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                maxLines = 1,
            )
            OutlinedTextField(
                modifier = outlinedTextFieldModifier,
                value = capacitor.uf,
                onValueChange = {
                    capacitor = capacitor.copy(uf = it)
                    fieldValues = FieldValues.UF
                },
                textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
                label = { Text(stringResource(id = R.string.home_text_box_enter_uf)) },
                trailingIcon = errorIcon(isError && fieldValues == FieldValues.UF),
                supportingText = errorText(isError && fieldValues == FieldValues.UF),
                isError = isError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                maxLines = 1,
            )
            AppDivider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)
            )
            TextBody(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp),
                text = stringResource(id = R.string.home_tolerance_label)
            )
            Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                TextLabel(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp),
                    text = tolerance
                )
                OutlinedDropDownMenu("Tolerance", Tolerance.entries)
            }
            AppDivider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)
            )
            AppTextButton(text = stringResource(id = R.string.home_button_calculate)) {
                focusManager.clearFocus()
                isError = !CapacitorValues.update(capacitor, fieldValues)
                reloadId++
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
