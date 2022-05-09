package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.event.AuthRequestOTPCodeEvent
import com.iagora.wingman.auth.otp.presentation.event.InputPhoneNumberDataEvent
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.common.presentation.event.FormValidationEvent
import com.iagora.wingman.common.presentation.ui.component.CommonPrimaryColorButton
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.component.OutlineTextFieldCustom
import com.iagora.wingman.destinations.InputOTPCodeScreenDestination
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.iagora.wingman.destinations.RegistrationWingmanDetailDataScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
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
    authRequestOTPCodeViewModel: AuthRequestOTPCodeViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val inputPhoneNumberState by authRequestOTPCodeViewModel.inputPhoneNumberState.collectAsState()
    val inputPhoneNumberEvent = authRequestOTPCodeViewModel.inputPhoneNumberEvents
    val authRequestOTPCodeEvent = authRequestOTPCodeViewModel.authRequestOTPCodeEvents
    val authenticationState by authRequestOTPCodeViewModel.authenticationState.collectAsState()
    val isWingmanCompleteDataState =
        authRequestOTPCodeViewModel.isWingmanCompleteDataState.collectAsState()

    // check user authentication status
    when {
        authenticationState.isLoading -> FullScreenLoadingIndicator()
        authenticationState.isError -> Text(text = "Error")
        authenticationState.isAuthenticated -> {
            // check user complete data
            if (!isWingmanCompleteDataState.value) {
                navigator.navigate(RegistrationWingmanDetailDataScreenDestination) {
                    popUpTo(
                        InputPhoneNumberWithApplicationLogoScreenDestination
                    ) {
                        inclusive = true
                    }
                }
            } else {
                navigateToRootScreen(navigator)
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        // navigate to input otp code screen after success with phone number input
        LaunchedEffect(key1 = scaffoldState) {
            inputPhoneNumberEvent.collect { event ->
                when (event) {
                    FormValidationEvent.Success -> authRequestOTPCodeViewModel.onInputFieldEvent(
                        InputPhoneNumberDataEvent.SendRequestOTPCode
                    )
                }
            }
        }

        LaunchedEffect(key1 = scaffoldState) {
            authRequestOTPCodeEvent.collect { event ->
                when (event) {
                    AuthRequestOTPCodeEvent.Error -> scaffoldState.snackbarHostState.showSnackbar(
                        inputPhoneNumberState.errorMessage?.asString(context).toString()
                    )
                    AuthRequestOTPCodeEvent.Success -> {
                        navigator.navigate(
                            InputOTPCodeScreenDestination(authRequestOTPCodeViewModel.inputPhoneNumberState.value.phoneNumber)
                        ) {
                            popUpTo(InputPhoneNumberWithApplicationLogoScreenDestination)
                        }
                    }
                }
            }
        }

        // default screen content
        InputPhoneNumberWithApplicationLogoContent(
            authRequestOTPCodeViewModel,
            inputPhoneNumberState
        )
    }
}


@ExperimentalCoroutinesApi
private fun navigateToRootScreen(navigator: DestinationsNavigator) {
    navigator.navigate(RootScreenDestination) {
        popUpTo(
            InputPhoneNumberWithApplicationLogoScreenDestination
        ) {
            inclusive = true
        }
    }
}

@Composable
private fun InputPhoneNumberWithApplicationLogoContent(
    authRequestOTPCodeViewModel: AuthRequestOTPCodeViewModel,
    inputPhoneNumberState: InputPhoneNumberState
) {
    val thisScrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = thisScrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.logo_wingman),
            contentDescription = stringResource(
                R.string.logo_wingman_desc
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(128.dp))
        Text(
            text = "Masuk / Daftar sebagai WINGMAN",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlineTextFieldCustom(
            textValue = inputPhoneNumberState.phoneNumber,
            textValueChange = {
                authRequestOTPCodeViewModel.onInputFieldEvent(
                    InputPhoneNumberDataEvent.PhoneNumberFieldChange(
                        it
                    )
                )
            },
            labelText = "Nomor HP (6285111222333)",
            isError = inputPhoneNumberState.phoneNumberFieldError != null,
            errorMessage = inputPhoneNumberState.phoneNumberFieldError?.asString(),
            keyboardType = KeyboardType.Phone,
            keyboardActionOnDone = {
                focusManager.clearFocus()
                authRequestOTPCodeViewModel.onInputFieldEvent(InputPhoneNumberDataEvent.Submit)
            }
        )
        Spacer(modifier = Modifier.size(24.dp))
        CommonPrimaryColorButton(
            clickEvent = { authRequestOTPCodeViewModel.onInputFieldEvent(InputPhoneNumberDataEvent.Submit) },
            buttonTitle = "LOGIN",
            isEnable = !inputPhoneNumberState.isLoading
        )
    }
}