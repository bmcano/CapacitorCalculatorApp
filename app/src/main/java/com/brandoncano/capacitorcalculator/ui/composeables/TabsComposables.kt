package com.brandoncano.capacitorcalculator.ui.composeables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CodeValueTabs(tabSelection: Int) {

    TabRow(
        selectedTabIndex = tabSelection,
        divider = {
            Spacer(modifier = Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            SecondaryIndicator(

                height = 5.dp,
//                color = Color.White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
//        tabData.forEachIndexed { index, s ->
//            Tab(
//                selected = tabSelection == index,
//                onClick = {
//                    scope.launch {
//                        pagerState.animateScrollToPage(index)
//                    }
//                },
//                icon = {
//                    Icon(imageVector = s.second, contentDescription = null)
//                }
//            )
//        }
    }
}