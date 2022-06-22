package com.iagora.wingman.user_profile.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapperCustom
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicatorCustom
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
import com.iagora.wingman.user_profile.presentation.component.UserProfileContent
import com.iagora.wingman.user_profile.presentation.view_model.UserProfileViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Destination
@Composable
fun UserProfileScreen(
    navigator: DestinationsNavigator,
    userProfileViewModel: UserProfileViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val logoutState by userProfileViewModel.logoutState.collectAsState()


    BottomNavigationContentWrapperCustom {
        Scaffold(scaffoldState = scaffoldState) {
            // user logout screen state
            when {
                logoutState.isError -> LaunchedEffect(scaffoldState) {
                    userProfileViewModel.userProfileErrors.collect { data ->
                        scaffoldState.snackbarHostState.showSnackbar(data.asString(context))
                    }
                }
                logoutState.isLoading -> FullScreenLoadingIndicatorCustom()
                logoutState.logoutSuccess -> navigator.navigate(
                    InputPhoneNumberWithApplicationLogoScreenDestination
                ) {
                    popUpTo(RootScreenDestination) {
                        inclusive = true
                    }
                }
            }

            // default user profile screen
            UserProfileContent(userProfileViewModel)
        }
    }
}