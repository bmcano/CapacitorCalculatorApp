package com.brandoncano.capacitorcalculator.ui.screens.about

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.composeables.AppDivider
import com.brandoncano.capacitorcalculator.ui.composeables.AppStandardCard
import com.brandoncano.capacitorcalculator.ui.composeables.ArrowButtonCard
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.ui.theme.textStyleHeadline
import com.brandoncano.capacitorcalculator.util.OpenLink

@Composable
fun AuthorCard() {
    AppStandardCard {
        HeadlineBodyStack(
            label = R.string.about_created_by,
            body = R.string.about_author,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun AppInfoCard() {
    AppStandardCard {
        HeadlineBodyStack(
            label = R.string.about_app_version,
            body = R.string.version,
        )
        AppDivider(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp))
        HeadlineBodyStack(
            label = R.string.about_last_updated_on,
            body = R.string.last_updated,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ViewPrivacyPolicy(context: Context) {
    ArrowButtonCard(
        Icons.Outlined.FileOpen,
        stringResource(id = R.string.about_view_privacy_policy),
    ) {
        OpenLink.openPrivacyPolicy(context)
    }
}

@Composable
fun DescriptionCard() {
    val modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)
    AppStandardCard {
        Text(
            text = stringResource(id = R.string.about_description),
            modifier = modifier,
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(id = R.string.about_description_part_01),
            modifier = modifier,
            style = textStyleBody(),
        )
        Text(
            text = stringResource(id = R.string.about_description_part_02),
            modifier = modifier,
            style = textStyleBody(),
        )
        Text(
            text = stringResource(id = R.string.about_description_part_03),
            modifier = modifier.padding(bottom = 12.dp),
            style = textStyleBody(),
        )
    }
}

@Composable
private fun HeadlineBodyStack(@StringRes label: Int, @StringRes body: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = label),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(id = body),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp),
            style = textStyleBody(),
        )
    }
}
