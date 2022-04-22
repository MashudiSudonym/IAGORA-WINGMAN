package com.iagora.wingman.dashboard.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.navArgument
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun DashBoardScreen(navigator: DestinationsNavigator) {

}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    WINGMANTheme {
//        DashBoardScreen()
    }
}