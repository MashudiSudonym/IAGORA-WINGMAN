package com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iagora.wingman.customer_care.presentation.CustomerCareScreen
import com.iagora.wingman.dashboard.presentation.DashBoardScreen
import com.iagora.wingman.user_profile.presentation.UserProfileScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun BottomNavigationBarNavigationConfigurations(
    navigator: DestinationsNavigator,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
        composable(BottomNavigationScreens.Home.route) {
            DashBoardScreen(navigator)
        }
        composable(BottomNavigationScreens.CustomerCare.route) {
            CustomerCareScreen(navigator)
        }
        composable(BottomNavigationScreens.Profile.route) {
            UserProfileScreen(navigator)
        }
    }
}