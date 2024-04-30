package com.brandoncano.capacitorcalculator.previews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brandoncano.capacitorcalculator.ui.composeables.TextBody
import com.brandoncano.capacitorcalculator.ui.composeables.TextHeadline
import com.brandoncano.capacitorcalculator.ui.composeables.TextLabel
import com.brandoncano.capacitorcalculator.ui.composeables.TextTitle
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextComponentsPreview() {
    CapacitorCalculatorTheme {
        Column {
            TextHeadline(text = "Headline")
            TextTitle(text = "Title")
            TextLabel(text = "Label")
            TextBody(text = "Body")
        }
    }
}