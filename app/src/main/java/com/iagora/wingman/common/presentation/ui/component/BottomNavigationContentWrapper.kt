package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// use this view component for the view to be displayed along with the bottom navigation bar
@Composable
fun BottomNavigationContentWrapper(
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 56.dp), content = content)
}
