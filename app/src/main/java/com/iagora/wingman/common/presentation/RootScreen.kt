package com.iagora.wingman.common.presentation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationBarNavigationConfigurations
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationScreen
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationScreens
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RootScreen(navigator: DestinationsNavigator) {
    val navHostController: NavHostController = rememberNavController()
    val bottomNavigationItems: List<BottomNavigationScreens> = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.CustomerCare,
        BottomNavigationScreens.Profile
    )

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            BottomNavigationScreen(
                navHostController = navHostController,
                items = bottomNavigationItems
            )
        }) {
        BottomNavigationBarNavigationConfigurations(navigator, navHostController)
    }
}