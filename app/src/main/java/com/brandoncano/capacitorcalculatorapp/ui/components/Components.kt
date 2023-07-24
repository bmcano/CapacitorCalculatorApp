package com.brandoncano.capacitorcalculatorapp.ui.components

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculatorapp.MainActivity
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.constants.Capacitor
import com.brandoncano.capacitorcalculatorapp.ui.navigation.Screen
import com.brandoncano.capacitorcalculatorapp.ui.theme.CapacitorCalculatorAppTheme
import com.brandoncano.capacitorcalculatorapp.util.EmailFeedback

@Composable
fun HomeAppBar(
    titleText: String,
    context: Context,
    navController: NavController
) {
    DefaultAppBar(titleText) {
        var expanded by remember { mutableStateOf(false) }
        // add another screen to show a chart of capacitor codes
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(R.string.content_description_more)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.menu_share)) },
                onClick = { }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.menu_feedback)) },
                onClick = {
                    EmailFeedback.execute(context)
                    expanded = !expanded
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.menu_clear)) },
                onClick = { }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.menu_about)) },
                onClick = { navController.navigate(Screen.About.route) }
            )
        }
    }
}

@Composable
fun AboutAppBar(
    titleText: String,
) {
    DefaultAppBar(titleText)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    titleText: String,
    actions: @Composable (RowScope.() -> Unit) = { }
) {
    val colors = centerAlignedTopAppBarColors(
        containerColor = colorScheme.primary,
        navigationIconContentColor = colorScheme.onPrimary,
        titleContentColor = colorScheme.onPrimary,
        actionIconContentColor = colorScheme.onPrimary
    )
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = actions,
        colors = colors,
    )
}

@Composable
fun BottomShadow(alpha: Float = 0.1f, height: Dp = 4.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Black.copy(alpha), Color.Transparent)
                )
            )
    )
}

@Composable
fun AppTextButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 32.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp,
            disabledElevation = 0.dp
        )
    ) {
        TextHeadline(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    label: String,
    interactionSource: MutableInteractionSource,
    capacitor: MutableState<Capacitor>,
) {
    var text by remember { mutableStateOf(value) }
    var buttonPressed by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 16.dp)
            .fillMaxWidth(),
        value = if (buttonPressed) value else text,
        onValueChange = {
            buttonPressed = false
            text = it
            capacitor.value.code = it
        },
        textStyle = TextStyle(fontFamily = FontFamily.SansSerif),
        label = { Text(label) },
        trailingIcon = { },
        supportingText = { },
        isError = false,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        maxLines = 1,
        interactionSource = interactionSource.also { interaction ->
            LaunchedEffect(interaction) {
                interaction.interactions.collect {
                    if (it is PressInteraction.Release) {
                        buttonPressed = true
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ComponentsPreview() {
    val app = MainActivity()
    CapacitorCalculatorAppTheme {
        Column {
            HomeAppBar(stringResource(R.string.app_name), app, NavController(app))
            BottomShadow(height = 4.dp)
            AboutAppBar(stringResource(R.string.about_title))
            BottomShadow(height = 4.dp)
            AppTextButton(text = "This is a button") { }
        }
    }
}
