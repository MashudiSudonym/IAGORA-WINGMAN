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
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.common.presentation.ui.component.CommonPrimaryColorButton
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.component.SingleLineOutlineTextFieldCustom
import com.iagora.wingman.destinations.InputOTPCodeScreenDestination
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination(start = true)
@Composable
fun InputPhoneNumberWithApplicationLogoScreen(
    navigator: DestinationsNavigator,
    authRequestOTPCodeViewModel: AuthRequestOTPCodeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val inputPhoneNumberState by authRequestOTPCodeViewModel.inputPhoneNumberState.collectAsState()
    val authenticationState by authRequestOTPCodeViewModel.authenticationState.collectAsState()

    // check user authentication status
    when {
        authenticationState.isLoading -> FullScreenLoadingIndicator()
        authenticationState.isError -> Text(text = "Error")
        authenticationState.isAuthenticated -> {
            navigator.navigate(RootScreenDestination) {
                popUpTo(
                    InputPhoneNumberWithApplicationLogoScreenDestination
                ) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        // navigate to input otp code screen after success with phone number input
        when {
            inputPhoneNumberState.isError -> LaunchedEffect(scaffoldState) {
                authRequestOTPCodeViewModel.inputPhoneNumberErrors.collect { data ->
                    scaffoldState.snackbarHostState.showSnackbar(data.asString(context))
                }
            }
            inputPhoneNumberState.isLoading -> FullScreenLoadingIndicator()
            inputPhoneNumberState.isSuccess -> {
                navigator.navigate(
                    InputOTPCodeScreenDestination(
                        authRequestOTPCodeViewModel.phoneNumberText
                    )
                ) {
                    popUpTo(InputPhoneNumberWithApplicationLogoScreenDestination)
                }

                /*
                * Reset field validation status
                * isSuccess to false
                */
                authRequestOTPCodeViewModel.onUpdatedValidationStatusChange(false)
                authRequestOTPCodeViewModel.changeValidationSuccessScreenStatus()
            }
        }

        // default screen content
        InputPhoneNumberWithApplicationLogoContent(
            authRequestOTPCodeViewModel,
            inputPhoneNumberState
        )
    }
}

@Composable
private fun InputPhoneNumberWithApplicationLogoContent(
    authRequestOTPCodeViewModel: AuthRequestOTPCodeViewModel,
    inputPhoneNumberState: InputPhoneNumberState
) {
    val thisScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
            .imePadding()
            .navigationBarsWithImePadding()
            .navigationBarsPadding()
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
        SingleLineOutlineTextFieldCustom(
            textValue = authRequestOTPCodeViewModel.phoneNumberText,
            textValueChange = authRequestOTPCodeViewModel::onPhoneNumberChange,
            labelText = "Nomor HP (6285111222333)",
            isError = inputPhoneNumberState.isTextFieldError,
            errorMessage = inputPhoneNumberState.errorMessage.asString(),
            keyboardType = KeyboardType.Phone,
            keyboardActionOnDone = { authRequestOTPCodeViewModel.validationPhoneNumberTextFieldAndSendOTPRequest() }
        )
        Spacer(modifier = Modifier.size(24.dp))
        CommonPrimaryColorButton(
            clickEvent = { authRequestOTPCodeViewModel.validationPhoneNumberTextFieldAndSendOTPRequest() },
            buttonTitle = "LOGIN",
            isEnable = !inputPhoneNumberState.isLoading
        )
    }
}