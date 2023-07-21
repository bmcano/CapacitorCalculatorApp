package com.brandoncano.capacitorcalculatorapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.ui.components.AppTextButton
import com.brandoncano.capacitorcalculatorapp.ui.components.BottomShadow
import com.brandoncano.capacitorcalculatorapp.ui.components.HomeAppBar
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme
import com.brandoncano.capacitorcalculatorapp.util.IsValidCode
import com.brandoncano.capacitorcalculatorapp.util.MultiplierFromDigit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(context: Context, navController: NavController) {
    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            var code by remember { mutableStateOf("") }
            var pf by remember { mutableStateOf("") }
            var nf by remember { mutableStateOf("") }
            var uf by remember { mutableStateOf("") }
            var isError by remember { mutableStateOf(false) }
            val focusManager = LocalFocusManager.current
            val outlinedTextFieldModifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                .fillMaxWidth()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeAppBar(context, navController)
                BottomShadow(height = 4.dp)

                OutlinedTextField(
                    modifier = outlinedTextFieldModifier,
                    label = { Text(stringResource(id = R.string.enter_code)) },
                    value = code,
                    onValueChange = { code = it },
                    isError = isError,
                    trailingIcon = { if (isError) Icon(Icons.Filled.Error, "error") },
                    supportingText = { if (isError) { Text(text = "Invalid code") } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                )
                OutlinedTextField(
                    modifier = outlinedTextFieldModifier,
                    label = { Text(stringResource(id = R.string.text_box_enter_pf)) },
                    value = pf,
                    onValueChange = { pf = it },
                    isError = isError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                )
                OutlinedTextField(
                    modifier = outlinedTextFieldModifier,
                    label = { Text(stringResource(id = R.string.text_box_enter_nf)) },
                    value = nf,
                    onValueChange = { nf = it },
                    isError = isError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                )
                OutlinedTextField(
                    modifier = outlinedTextFieldModifier,
                    label = { Text(stringResource(id = R.string.text_box_enter_uf)) },
                    value = uf,
                    onValueChange = { uf = it },
                    isError = isError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1
                )

                AppTextButton(text = stringResource(id = R.string.button_calculate)) {
                    focusManager.clearFocus()
                    // starting with code only
                    if (IsValidCode.execute(code)) {
                        isError = false
                        val number = if (code.length == 3) code.dropLast(1).toInt() else code.toInt()
                        val multiplier = if (code.length == 3) code.takeLast(1) else "0"
                        val pico = number * MultiplierFromDigit.execute(multiplier)
                        pf = "$pico"
                        nf = "${pico.toDouble()/1000}"
                        uf = "${pico.toDouble()/1000000}"
                    } else {
                        isError = true
                    }
                }
            }
        }
    }
}

