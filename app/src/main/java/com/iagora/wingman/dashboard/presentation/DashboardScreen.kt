package com.iagora.wingman.dashboard.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val dashboardAuthenticationState by dashboardViewModel.dashboardAuthenticationState.collectAsState()

    // check user authentication status
    when {
        dashboardAuthenticationState.isLoading -> FullScreenLoadingIndicator()
        dashboardAuthenticationState.isError -> Text(text = "Error")
        !dashboardAuthenticationState.isAuthenticated -> {
            navigator.navigate(InputPhoneNumberWithApplicationLogoScreenDestination) {
                popUpTo(
                    DashBoardScreenDestination
                ) {
                    inclusive = true
                }
            }
        }
    }

    // dashboard content
    Text(text = "Dashboard")
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    WINGMANTheme {
//        DashBoardScreen()
    }
}