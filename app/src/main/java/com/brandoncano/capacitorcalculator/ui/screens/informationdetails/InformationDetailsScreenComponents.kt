package com.brandoncano.capacitorcalculator.ui.screens.informationdetails

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.composeables.AppComponentPreviews
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.black
import com.brandoncano.capacitorcalculator.ui.theme.gray
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline
import com.brandoncano.capacitorcalculator.ui.theme.textStyleTitle
import com.brandoncano.capacitorcalculator.ui.theme.white

@Composable
fun CeramicCapacitorImage() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_capacitor),
            contentDescription = stringResource(id = R.string.content_description_ceramic_capacitor),
            modifier = Modifier.size(196.dp),
        )
        Text(
            text = "103",
            style = textStyleTitle().white()
        )
    }
}

@Composable
fun ElectrolyticCapacitorImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.img_equation),
                contentDescription = stringResource(id = R.string.content_description_equation),
                modifier = Modifier.size(196.dp),
                colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) white else black),
            )
            Image(
                painter = painterResource(id = R.drawable.img_electrolytic_capacitor),
                contentDescription = stringResource(id = R.string.content_description_electrolytic_capacitor),
                modifier = Modifier.size(196.dp),
            )
        }
    }
}

@Composable
fun HeaderBodyInformation(@StringRes header: Int, @StringRes body: Int, top: Boolean = false) {
    Column {
        Text(
            text = stringResource(id = header),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = if (top) 8.dp else 24.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(id = body),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleBody().gray(),
        )
    }
}

@AppComponentPreviews
@Composable
fun ElectrolyticCapacitorImagePreview() {
    CapacitorCalculatorTheme {
        ElectrolyticCapacitorImage()
    }
}
