package com.brandoncano.capacitorcalculatorapp.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.capacitorcalculatorapp.R
import com.brandoncano.capacitorcalculatorapp.ui.navigation.Screen
import com.brandoncano.capacitorcalculatorapp.util.EmailFeedback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(context: Context, navController: NavController) {
    val colors = centerAlignedTopAppBarColors(
        containerColor = colorScheme.primary,
        navigationIconContentColor = colorScheme.onPrimary,
        titleContentColor = colorScheme.onPrimary,
        actionIconContentColor = colorScheme.onPrimary
    )
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            var expanded by remember { mutableStateOf(false) }
            // add another screen to show a chart of capacitor codes
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
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
        },
        colors = colors,
    )
}

@Composable
fun BottomShadow(alpha: Float = 0.1f, height: Dp = 8.dp) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(Color.Black.copy(alpha = alpha), Color.Transparent)
            )
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(hint: String, value: String = "") {
    var text by rememberSaveable { mutableStateOf(value) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 16.dp)
            .fillMaxWidth()
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
