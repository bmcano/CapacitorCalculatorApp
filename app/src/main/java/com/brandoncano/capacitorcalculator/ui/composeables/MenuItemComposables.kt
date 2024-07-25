package com.brandoncano.capacitorcalculator.ui.composeables

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.util.EmailFeedback
import com.brandoncano.capacitorcalculator.util.ShareCapacitance

@Composable
fun ClearSelectionsMenuItem(interactionSource: MutableInteractionSource, onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_clear) },
        onClick = onClick,
        interactionSource = interactionSource,
    )
}

@Composable
fun ShareMenuItem(text: String, context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share) },
        onClick = { ShareCapacitance.execute(text, context) },
        interactionSource = interactionSource,
    )
}

@Composable
fun FeedbackMenuItem(context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_feedback) },
        onClick = { EmailFeedback.execute(context) },
        interactionSource = interactionSource,
    )
}

@Composable
fun AboutAppMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = { navController.navigate(Screen.About.route) },
        interactionSource = interactionSource,
    )
}

@Composable
private fun MenuText(@StringRes stringRes: Int) {
    Text(
        text = stringResource(id = stringRes),
        style = textStyleBody(),
    )
}

@AppComponentPreviews
@Composable
private fun MenuItemsPreview() {
    val interactionSource = remember { MutableInteractionSource() }
    val app = MainActivity()
    CapacitorCalculatorTheme {
        Column {
            AboutAppMenuItem(NavController(app), interactionSource)
            ClearSelectionsMenuItem(interactionSource) { }
            FeedbackMenuItem(app, interactionSource)
            ShareMenuItem("text", app, interactionSource)
        }
    }
}
