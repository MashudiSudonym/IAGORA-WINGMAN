package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.state.CountDownState
import com.iagora.wingman.auth.otp.presentation.state.InputOTPCodeState
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.destinations.DashBoardScreenDestination
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun InputOTPCodeScreen(
    navigator: DestinationsNavigator,
    phoneNumber: String,
    authVerifyOTPCodeViewModel: AuthVerifyOTPCodeViewModel = hiltViewModel()
) {
    val inputOTPCodeState by authVerifyOTPCodeViewModel.inputOTPCodeState.collectAsState()
    val countDownState by authVerifyOTPCodeViewModel.countDownState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    // running count down timer
    authVerifyOTPCodeViewModel.countDownTimer()

    Scaffold(scaffoldState = scaffoldState) {
        // navigate to registration screen after success with otp code verification
        when {
            inputOTPCodeState.isError -> LaunchedEffect(scaffoldState) {
                authVerifyOTPCodeViewModel.inputOTPCodeErrors.collect { data ->
                    scaffoldState.snackbarHostState.showSnackbar(data.asString(context))
                }
            }
            inputOTPCodeState.isLoading -> FullScreenLoadingIndicator()
            inputOTPCodeState.isSuccess -> {
                // check if not user complete registration, open registration screen
                if (!inputOTPCodeState.isCompleteRegister) {
                    navigator.navigate(
                        DashBoardScreenDestination
                    ) {
                        popUpTo(InputPhoneNumberWithApplicationLogoScreenDestination) {
                            inclusive = true
                        }
                    }
                }

                // if user complete registration, open dashboard screen
                navigator.navigate(
                    DashBoardScreenDestination
                ) {
                    popUpTo(InputPhoneNumberWithApplicationLogoScreenDestination) {
                        inclusive = true
                    }
                }

                /*
                * Reset field validation status
                * isSuccess to false
                */
                authVerifyOTPCodeViewModel.onUpdatedValidationStatusChange(false)
                authVerifyOTPCodeViewModel.changeValidationSuccessScreenStatus()
            }
        }

        InputOTPCodeContent(
            phoneNumber,
            authVerifyOTPCodeViewModel,
            inputOTPCodeState,
            countDownState
        )
    }
}

@Composable
private fun InputOTPCodeContent(
    phoneNumber: String,
    authVerifyOTPCodeViewModel: AuthVerifyOTPCodeViewModel,
    inputOTPCodeState: InputOTPCodeState,
    countDownState: CountDownState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .navigationBarsWithImePadding()
            .verticalScroll(state = rememberScrollState()),
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
        Spacer(modifier = Modifier.size(64.dp))
        Text(
            text = "Masukkan Kode OTP",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Kami telah mengirimkan kode OTP pada nomor WA $phoneNumber silahkan dicek",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(64.dp))
        Column {
            OutlinedTextField(
                value = authVerifyOTPCodeViewModel.otpCodeText,
                onValueChange = authVerifyOTPCodeViewModel::onOTPCodeChange,
                label = { Text(text = "Masukkan 6 digit kode OTP Anda") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Number
                ),
                isError = inputOTPCodeState.isTextFieldError,
                trailingIcon = {
                    if (inputOTPCodeState.isTextFieldError) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            tint = MaterialTheme.colors.error,
                            contentDescription = null
                        )
                    }
                })
            if (inputOTPCodeState.isTextFieldError) {
                Text(
                    text = inputOTPCodeState.errorMessage.asString(),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            enabled = !inputOTPCodeState.isLoading,
            onClick = {
                authVerifyOTPCodeViewModel.validationOTPCodeTextFieldAndSendOTPVerification(
                    phoneNumber
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "KIRIM OTP",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(modifier = Modifier.size(64.dp))
        // If count down timer it's end, show up this button
        if (countDownState.isCountDownStop) {
            TextButton(onClick = { authVerifyOTPCodeViewModel.requestNewOTPCode(phoneNumber) }) {
                Text(
                    text = "MINTA OTP",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary
                )
            }
        } else {
            Text(
                text = "BELUM MENDAPATKAN TOKEN ? MINTA KEMBALI DALAM ${Constants.START_COUNT_DOWN} DETIK",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primary,
            )
        }
    }
}