package com.brandoncano.capacitorcalculatorapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme

@Preview(showBackground = true)
@Composable
fun HomeLayout() {
    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(hint = "Code")
                OutlinedTextField(hint = "pF")
                OutlinedTextField(hint = "nF")
                OutlinedTextField(hint = "uF")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutLayout() {
    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextBody(
                    stringResource(id = R.string.app_name),
                    24.sp
                )
            }
        }
    }
}

@Composable
fun TextBody(text: String, fontSize: TextUnit = 16.sp) {
    Text(
        text = text,
        fontSize = fontSize,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(hint: String, value: String = "") {
    var text by remember { mutableStateOf(TextFieldValue(value)) }
    OutlinedTextField(
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = hint) },
        onValueChange = { text = it },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
fun AppButton() {

}

@Composable
fun DisplayText() {

}

