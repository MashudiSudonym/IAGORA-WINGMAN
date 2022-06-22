package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// use this view component for the view to be displayed along with the bottom navigation bar
@Composable
fun BottomNavigationContentWrapperCustom(
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 56.dp), content = content)
}
