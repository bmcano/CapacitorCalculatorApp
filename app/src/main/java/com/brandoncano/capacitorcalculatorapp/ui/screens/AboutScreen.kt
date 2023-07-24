package com.brandoncano.capacitorcalculatorapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.ui.components.BottomShadow
import com.brandoncano.capacitorcalculatorapp.ui.components.AboutAppBar
import com.brandoncano.capacitorcalculatorapp.ui.components.TextBody
import com.brandoncano.capacitorcalculatorapp.ui.components.TextHeadline
import com.brandoncano.capacitorcalculatorapp.ui.components.TextTitle
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme

@Composable
fun AboutScreen(context: Context, navController: NavController) {
    CapacitorCalculatorAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AboutAppBar(stringResource(R.string.about_title))
                BottomShadow()

                val textModifierBody = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, end = 16.dp)
                val textModifier = textModifierBody.padding(top = 16.dp)
                val cardModifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth()

                Card(modifier = cardModifier) {
                    TextTitle(
                        modifier = textModifier,
                        text = stringResource(id = R.string.app_name)
                    )
                    TextHeadline(
                        modifier = textModifier,
                        text = stringResource(id = R.string.about_app_version)
                    )
                    TextBody(
                        modifier = textModifierBody,
                        text = stringResource(id = R.string.about_version)
                    )
                    TextHeadline(
                        modifier = textModifier,
                        text = stringResource(id = R.string.about_created_by)
                    )
                    TextBody(
                        modifier = textModifierBody,
                        text = stringResource(id = R.string.about_author)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Card(modifier = cardModifier) {
                    TextHeadline(
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
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}