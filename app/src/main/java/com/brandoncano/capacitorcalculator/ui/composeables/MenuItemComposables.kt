package com.brandoncano.capacitorcalculator.ui.composeables

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.navigation.Screen
import com.brandoncano.capacitorcalculator.ui.MainActivity
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.menuText
import com.brandoncano.capacitorcalculator.ui.theme.textStyleBody
import com.brandoncano.capacitorcalculator.util.EmailFeedback
import com.brandoncano.capacitorcalculator.util.ShareCapacitance

/**
 * Note: Menu items are in alphabetical order
 */

@Composable
fun AboutAppMenuItem(navController: NavController, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = {
            showMenu.value = false
            navController.navigate(Screen.About.route)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Info) },
    )
}

@Composable
fun ClearSelectionsMenuItem(onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_clear) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Cancel) },
    )
}

@Composable
fun FeedbackMenuItem(context: Context, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_feedback) },
        onClick = {
            showMenu.value = false
            EmailFeedback.execute(context)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Feedback) },
    )
}

@Composable
fun ShareMenuItem(text: String, context: Context, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share) },
        onClick = {
            showMenu.value = false
            ShareCapacitance.execute(text, context)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Share) },
    )
}

@Composable
private fun MenuText(@StringRes stringRes: Int) {
    Text(
        text = stringResource(id = stringRes),
        style = textStyleBody().menuText(),
    )
}

@Composable
private fun MenuIcon(imageVector: ImageVector) {
    Image(
        imageVector = imageVector,
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
    )
}

@AppComponentPreviews
@Composable
private fun MenuItemsPreview() {
    val showMenu = remember { mutableStateOf(false) }
    val app = MainActivity()
    CapacitorCalculatorTheme {
        Column {
            AboutAppMenuItem(NavController(app), showMenu)
            ClearSelectionsMenuItem { }
            FeedbackMenuItem(app, showMenu)
            ShareMenuItem("text", app, showMenu)
        }
    }
}
