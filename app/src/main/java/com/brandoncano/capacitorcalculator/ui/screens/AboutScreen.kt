package com.brandoncano.capacitorcalculator.ui.screens

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.AddToHomeScreen
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.composeables.AppDivider
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.composeables.DefaultCard
import com.brandoncano.capacitorcalculator.ui.composeables.LabelBodyText
import com.brandoncano.capacitorcalculator.ui.composeables.LabelBodyTextCard
import com.brandoncano.capacitorcalculator.ui.composeables.TextBody
import com.brandoncano.capacitorcalculator.ui.composeables.TextLabel
import com.brandoncano.capacitorcalculator.ui.composeables.TitleAppBar
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.util.PlayStore

/**
 * Job: Holds all the content for the about screen
 */

@Composable
fun AboutScreen(context: Context) {
    CapacitorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) { Content(context) }
    }
}

@Composable
private fun Content(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        TitleAppBar(stringResource(R.string.about_title))
        val textModifier = Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)

        LabelBodyTextCard(
            label = R.string.about_created_by,
            body = R.string.about_author,
        )

        DefaultCard {
            LabelBodyText(
                label = R.string.about_app_version,
                body = R.string.about_version,
            )
            AppDivider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            LabelBodyText(
                label = R.string.about_last_updated_on,
                body = R.string.about_last_update_date,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        DefaultCard {
            TextLabel(
                modifier = textModifier,
                text = stringResource(id = R.string.about_description)
            )
            TextBody(
                modifier = textModifier,
                text = stringResource(id = R.string.about_description_part_01)
            )
            TextBody(
                modifier = textModifier,
                text = stringResource(id = R.string.about_description_part_02)
            )
            TextBody(
                modifier = textModifier,
                text = stringResource(id = R.string.about_description_part_03)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        ArrowButtonCard(
            listOf(Icons.Outlined.Star, Icons.AutoMirrored.Outlined.AddToHomeScreen),
            listOf(
                stringResource(id = R.string.about_rate_this_app),
                stringResource(id = R.string.about_view_resistor_app)
            ),
            listOf(
                { PlayStore.openCapacitorApp(context) },
                { PlayStore.openResistorApp(context) }
            ),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutPreview() {
    val app = MainActivity()
    AboutScreen(app)
}
