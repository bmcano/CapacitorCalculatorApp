package com.brandoncano.capacitorcalculator.ui.composeables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Looks
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.capacitorcalculator.R
import com.brandoncano.capacitorcalculator.ui.theme.CapacitorCalculatorTheme
import com.brandoncano.capacitorcalculator.ui.theme.textStyleCaption

@Composable
fun SmdNavigationBar(
    selection: Int = 0,
    onClick: (Int) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(selection) }
    val labels = listOf(
        R.string.navbar_three_eia,
        R.string.navbar_four_eia,
        R.string.navbar_eia_198,
    )

    val icons = listOf(
        Icons.Outlined.Looks3,
        Icons.Outlined.Looks4,
        Icons.Outlined.Looks
    )
    NavigationBar {
        labels.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Image(
                        imageVector = icons[index],
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item),
                        style = textStyleCaption(),
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClick(selectedItem)
                }
            )
        }
    }
}

@AppComponentPreviews
@Composable
private fun SmdNavigationBarPreview() {
    CapacitorCalculatorTheme {
        SmdNavigationBar { }
    }
}