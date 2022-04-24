package com.iagora.wingman.common.presentation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationBarNavigationConfigurations
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationScreen
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationScreens
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination(start = true)
@Composable
fun RootScreen(
    navigator: DestinationsNavigator,
    rootViewModel: RootViewModel = hiltViewModel()
) {
    val navHostController: NavHostController = rememberNavController()
    val bottomNavigationItems: List<BottomNavigationScreens> = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.CustomerCare,
        BottomNavigationScreens.Profile
    )
    val dashboardAuthenticationState by rootViewModel.rootAuthenticationState.collectAsState()

    // check user authentication status
    when {
        dashboardAuthenticationState.isLoading -> FullScreenLoadingIndicator()
        dashboardAuthenticationState.isError -> Text(text = "Error")
        !dashboardAuthenticationState.isAuthenticated -> {
            navigator.navigate(InputPhoneNumberWithApplicationLogoScreenDestination) {
                popUpTo(
                    RootScreenDestination
                ) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .navigationBarsWithImePadding()
            .statusBarsPadding(),
        bottomBar = {
            BottomNavigationScreen(
                navHostController = navHostController,
                items = bottomNavigationItems
            )
        }) {
        BottomNavigationBarNavigationConfigurations(navHostController = navHostController)
    }
}