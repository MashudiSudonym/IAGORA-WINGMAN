package com.iagora.wingman.dashboard.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.iagora.wingman.destinations.DashBoardScreenDestination
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import timber.log.Timber

@RootNavGraph(start = true)
@Destination
@Composable
fun DashBoardScreen(
    navigator: DestinationsNavigator,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    val state = dashboardViewModel.state

    if (state.isLoading) {
        FullScreenLoadingIndicator()
    }

    if (state.isError) {
        Text(text = "Error")
    }

    if (!state.isAuthenticated) {
        navigator.navigate(InputPhoneNumberWithApplicationLogoScreenDestination) {
            popUpTo(
                DashBoardScreenDestination
            ) {
                inclusive = true
            }
        }
    }

    Text(text = "Dashboard")
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    WINGMANTheme {
//        DashBoardScreen()
    }
}