package com.iagora.wingman.dashboard.presentation

import androidx.compose.runtime.Composable
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapper
import com.iagora.wingman.dashboard.presentation.component.DashboardContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DashBoardScreen(navigator: DestinationsNavigator) {
    // dashboard content
    BottomNavigationContentWrapper {
        DashboardContent()
    }
}