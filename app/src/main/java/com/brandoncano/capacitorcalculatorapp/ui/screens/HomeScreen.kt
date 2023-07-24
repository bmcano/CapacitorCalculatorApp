package com.brandoncano.capacitorcalculatorapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.constants.Capacitor
import com.brandoncano.capacitorcalculatorapp.constants.FieldValues
import com.brandoncano.capacitorcalculatorapp.ui.components.AppDivider
import com.brandoncano.capacitorcalculatorapp.ui.components.AppTextButton
import com.brandoncano.capacitorcalculatorapp.ui.components.BottomShadow
import com.brandoncano.capacitorcalculatorapp.ui.components.HomeAppBar
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme
import com.brandoncano.capacitorcalculatorapp.util.CapacitorValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(context: Context, navController: NavController) {

    val capacitor = Capacitor()
    var code by remember { mutableStateOf("") }
    var pf by remember { mutableStateOf("") }
    var nf by remember { mutableStateOf("") }
    var uf by remember { mutableStateOf("") }
    var fieldValues: FieldValues by remember { mutableStateOf(FieldValues.Code) }

    CapacitorCalculatorAppTheme {
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

                HomeAppBar(stringResource(R.string.app_name), context, navController)
                BottomShadow()
                Card(
                    modifier = Modifier.padding(16.dp)
                ) {
                    OutlinedTextField(
                        modifier = outlinedTextFieldModifier,
                        value = code,
                        onValueChange = {
                            code = it
                            capacitor.code = it
                            fieldValues = FieldValues.Code
                        },
                        textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
                        label = { Text(stringResource(id = R.string.text_box_enter_code)) },
                        trailingIcon = { },
                        supportingText = { },
                        isError = false,
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
                        label = { Text(stringResource(id = R.string.text_box_enter_pf)) },
                        trailingIcon = { },
                        supportingText = { },
                        isError = false,
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
                        label = { Text(stringResource(id = R.string.text_box_enter_nf)) },
                        trailingIcon = { },
                        supportingText = { },
                        isError = false,
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
                        label = { Text(stringResource(id = R.string.text_box_enter_uf)) },
                        trailingIcon = { },
                        supportingText = { },
                        isError = false,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        maxLines = 1,
                    )
                    AppDivider(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)
                    )
                    AppTextButton(text = stringResource(id = R.string.button_calculate)) {
                        focusManager.clearFocus()
                        CapacitorValues.update(capacitor, fieldValues)
                        code = capacitor.code
                        pf = capacitor.pf
                        nf = capacitor.nf
                        uf = capacitor.uf
                    }
                }
            }
        }
    }
}
