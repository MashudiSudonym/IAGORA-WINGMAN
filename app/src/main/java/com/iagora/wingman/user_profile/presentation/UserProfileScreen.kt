package com.iagora.wingman.user_profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapper
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
import com.iagora.wingman.user_profile.presentation.component.HeaderUserProfileContent
import com.iagora.wingman.user_profile.presentation.component.WingmanUserProfileMenu
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun UserProfileScreen(
    navigator: DestinationsNavigator,
    userProfileViewModel: UserProfileViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val logoutState by userProfileViewModel.logoutState.collectAsState()


    BottomNavigationContentWrapper {
        Scaffold(scaffoldState = scaffoldState) { padding ->
            // user logout screen state
            when {
                logoutState.isError -> LaunchedEffect(scaffoldState) {
                    userProfileViewModel.userProfileErrors.collect { data ->
                        scaffoldState.snackbarHostState.showSnackbar(data.asString(context))
                    }
                }
                logoutState.isLoading -> FullScreenLoadingIndicator()
                logoutState.logoutSuccess -> navigator.navigate(
                    InputPhoneNumberWithApplicationLogoScreenDestination
                ) {
                    popUpTo(RootScreenDestination) {
                        inclusive = true
                    }
                }
            }
            // default user profile screen
            UserProfileContent(userProfileViewModel, padding)
        }
    }
}

@Composable
private fun UserProfileContent(userProfileViewModel: UserProfileViewModel, padding: PaddingValues) {
    val thisScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(thisScrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        HeaderUserProfileContent()
        Spacer(modifier = Modifier.size(48.dp))
        WingmanUserProfileMenu(userProfileViewModel)
    }
}