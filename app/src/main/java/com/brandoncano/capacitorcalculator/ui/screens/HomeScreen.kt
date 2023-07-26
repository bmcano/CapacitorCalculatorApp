package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.components.Capacitor
import com.brandoncano.capacitorcalculator.components.FieldValues
import com.brandoncano.capacitorcalculator.ui.components.AboutAppMenuItem
import com.brandoncano.capacitorcalculator.ui.components.AppDivider
import com.brandoncano.capacitorcalculator.ui.components.AppTextButton
import com.brandoncano.capacitorcalculator.ui.components.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.components.DefaultCard
import com.brandoncano.capacitorcalculator.ui.components.FeedbackMenuItem
import com.brandoncano.capacitorcalculator.ui.components.MenuAppBar
import com.brandoncano.capacitorcalculator.ui.components.TextBody
import com.brandoncano.capacitorcalculator.ui.components.errorIcon
import com.brandoncano.capacitorcalculator.ui.components.errorText
import com.brandoncano.capacitorcalculator.ui.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.util.CapacitorValues

/**
 * Job: Holds all the content for the home screen
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(context: Context, navController: NavController) {

    val capacitor = Capacitor()
    var isError by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf("") }
    var pf by remember { mutableStateOf("") }
    var nf by remember { mutableStateOf("") }
    var uf by remember { mutableStateOf("") }
    var fieldValues: FieldValues by remember { mutableStateOf(FieldValues.Code) }
    val interactionSource = remember { MutableInteractionSource() }

    CapacitorCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            val focusManager = LocalFocusManager.current
            val outlinedTextFieldModifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .fillMaxWidth()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MenuAppBar(stringResource(R.string.app_name), interactionSource) {
                    DropdownMenuItem(
                        text = { TextBody(text = stringResource(R.string.menu_clear)) },
                        onClick = {
                            capacitor.clear()
                            code = ""
                            pf = ""
                            nf = ""
                            uf = ""
                        },
                        interactionSource = interactionSource,
                    )
                    FeedbackMenuItem(context, interactionSource)
                    AboutAppMenuItem(navController, interactionSource)
                }

                DefaultCard {
                    OutlinedTextField(
                        modifier = outlinedTextFieldModifier,
                        value = code,
                        onValueChange = {
                            code = it
                            capacitor.code = it
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
                        value = pf,
                        onValueChange = {
                            pf = it
                            capacitor.pf = it
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
                        value = nf,
                        onValueChange = {
                            nf = it
                            capacitor.nf = it
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
                        value = uf,
                        onValueChange = {
                            uf = it
                            capacitor.uf = it
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
                    AppTextButton(text = stringResource(id = R.string.home_button_calculate)) {
                        focusManager.clearFocus()
                        isError = !CapacitorValues.update(capacitor, fieldValues)
                        code = capacitor.code
                        pf = capacitor.pf
                        nf = capacitor.nf
                        uf = capacitor.uf
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
    }
}
