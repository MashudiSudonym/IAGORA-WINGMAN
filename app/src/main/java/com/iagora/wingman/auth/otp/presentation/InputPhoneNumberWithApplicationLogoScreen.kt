package com.iagora.wingman.auth.otp.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.auth.otp.presentation.component.InputPhoneNumberWithApplicationLogoContent
import com.iagora.wingman.auth.otp.presentation.event.AuthRequestOTPCodeStatusEvent
import com.iagora.wingman.auth.otp.presentation.event.InputPhoneNumberDataEvent
import com.iagora.wingman.common.presentation.event.FormValidationEvent
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.util.Routing
import com.iagora.wingman.destinations.InputOTPCodeScreenDestination
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@RootNavGraph(start = true)
@Destination
@Composable
fun InputPhoneNumberWithApplicationLogoScreen(
    navigator: DestinationsNavigator,
    routing: Routing = Routing,
    authRequestOTPCodeViewModel: AuthRequestOTPCodeViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val inputPhoneNumberState by authRequestOTPCodeViewModel.inputPhoneNumberState.collectAsState()
    val inputPhoneNumberEvent = authRequestOTPCodeViewModel.inputPhoneNumberEvents
    val authRequestOTPCodeStatusEvent = authRequestOTPCodeViewModel.authRequestOTPCodeStatusEvents
    val authenticationState by authRequestOTPCodeViewModel.authenticationState.collectAsState()
    val isWingmanCompleteDataState by authRequestOTPCodeViewModel.isWingmanCompleteDataState.collectAsState()

    // check user authentication status
    when {
        authenticationState.isLoading -> FullScreenLoadingIndicator()
        authenticationState.isError -> Text(text = "Error")
        authenticationState.isAuthenticated -> {
            // check user complete data
            if (!isWingmanCompleteDataState) {
                routing.navigateToWingmanDetailDataFormScreen(navigator)
            } else {
                routing.navigateToRootScreen(navigator)
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        // send function request otp code
        LaunchedEffect(key1 = scaffoldState) {
            inputPhoneNumberEvent.collect { event ->
                when (event) {
                    FormValidationEvent.Success -> authRequestOTPCodeViewModel.onInputFieldEvent(
                        InputPhoneNumberDataEvent.SendRequestOTPCode
                    )
                }
            }
        }

        // navigate to input otp code screen after success with phone number input or error message when error
        LaunchedEffect(key1 = scaffoldState) {
            authRequestOTPCodeStatusEvent.collect { event ->
                when (event) {
                    AuthRequestOTPCodeStatusEvent.Error -> scaffoldState.snackbarHostState.showSnackbar(
                        inputPhoneNumberState.errorMessage?.asString(context).toString()
                    )
                    AuthRequestOTPCodeStatusEvent.Success -> {
                        navigator.navigate(
                            InputOTPCodeScreenDestination(authRequestOTPCodeViewModel.inputPhoneNumberState.value.phoneNumber)
                        ) {
                            popUpTo(InputPhoneNumberWithApplicationLogoScreenDestination)
                        }
                    }
                }
            }
        }

        // is Loading ?
        if (inputPhoneNumberState.isLoading) {
            FullScreenLoadingIndicator()
        }

        // default screen content
        InputPhoneNumberWithApplicationLogoContent(
            authRequestOTPCodeViewModel,
            inputPhoneNumberState
        )
    }
}