package com.iagora.wingman.dashboard.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapper
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun DashBoardScreen() {
    // dashboard content
    BottomNavigationContentWrapper {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            for (i in 1..45) {
                Text(text = "dashboard $i")
            }
        }
    }
}

@Composable
private fun DashboardContent() {

}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    WINGMANTheme {
        DashboardContent()
    }
}