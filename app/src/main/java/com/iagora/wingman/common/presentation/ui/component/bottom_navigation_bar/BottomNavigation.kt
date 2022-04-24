package com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationScreen(
    navHostController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation(
        backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            BottomNavigationItem(
                icon = screen.icon,
                label = {
                    Text(text = stringResource(id = screen.resId))
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                selectedContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                unselectedContentColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.4f) else Color.Black.copy(
                    alpha = 0.4f
                ),
                alwaysShowLabel = true,
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}