package com.iagora.wingman.common.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationBarNavigationConfigurations
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationScreen
import com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar.BottomNavigationScreens
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun RootScreen() {
    val navHostController: NavHostController = rememberNavController()
    val bottomNavigationItems: List<BottomNavigationScreens> = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.CustomerCare,
        BottomNavigationScreens.Profile
    )

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .imePadding()
            .navigationBarsWithImePadding()
            .navigationBarsPadding(),
        bottomBar = {
            BottomNavigationScreen(
                navHostController = navHostController,
                items = bottomNavigationItems
            )
        }) {
        BottomNavigationBarNavigationConfigurations(navHostController = navHostController)
    }
}