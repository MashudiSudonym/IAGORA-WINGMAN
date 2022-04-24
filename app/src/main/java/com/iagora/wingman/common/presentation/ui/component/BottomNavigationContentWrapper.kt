package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// use this view component for the view to be displayed along with the bottom navigation bar
@Composable
fun BottomNavigationContentWrapper(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(bottom = 56.dp), content = content)
}
