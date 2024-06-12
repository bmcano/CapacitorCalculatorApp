package com.brandoncano.capacitorcalculator.ui.composeables

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

@Composable
fun LabelBodyTextCard(@StringRes label: Int, @StringRes body: Int) {
    DefaultCard {
        LabelBodyText(label, body)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun LabelBodyText(@StringRes label: Int, @StringRes body: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        val modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        TextLabel(
            modifier = modifier.padding(top = 16.dp),
            text = stringResource(id = label)
        )
        TextBody(
            modifier = modifier,
            text = stringResource(id = body)
        )
    }
}

@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LabelBodyTextCardPreview() {
    CapacitorCalculatorTheme {
        Column(
            modifier = Modifier.height(82.dp)
        ) {
            LabelBodyTextCard(
                label = R.string.about_created_by,
                body = R.string.about_author,
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LabelBodyTextPreview() {
    CapacitorCalculatorTheme {
        Column(
            modifier = Modifier.height(64.dp)
        ) {
            LabelBodyText(
                label = R.string.about_created_by,
                body = R.string.about_author,
            )
        }
    }
}
