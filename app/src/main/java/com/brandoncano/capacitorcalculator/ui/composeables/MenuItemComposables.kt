package com.brandoncano.capacitorcalculator.ui.composeables

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.model.Capacitor
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.util.EmailFeedback
import com.brandoncano.capacitorcalculator.util.ShareCapacitance

@Composable
fun ClearSelectionsMenuItem(interactionSource: MutableInteractionSource, onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_clear),
            style = textStyleBody(),
        ) },
        onClick = onClick,
        interactionSource = interactionSource,
    )
}

@Composable
fun ShareMenuItem(capacitor: Capacitor, context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_share),
            style = textStyleBody(),
        ) },
        onClick = { ShareCapacitance.execute(capacitor.toString(), context)  },
        interactionSource = interactionSource,
    )
}

@Composable
fun FeedbackMenuItem(context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_feedback),
            style = textStyleBody(),
        ) },
        onClick = { EmailFeedback.execute(context) },
        interactionSource = interactionSource,
    )
}

@Composable
fun AboutAppMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_about),
            style = textStyleBody(),
        ) },
        onClick = { navController.navigate(Screen.About.route) },
        interactionSource = interactionSource,
    )
}
