package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.destinations.InputOTPCodeScreenDestination
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun InputPhoneNumberWithApplicationLogoScreen(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
//    val context = LocalContext.current

    Scaffold(scaffoldState = scaffoldState) {

        val inputPhoneNumberState by authViewModel.inputPhoneNumberState.collectAsState()

        // navigate to input otp code screen after success with phone number input
        when {
            inputPhoneNumberState.isSuccess -> {
                navigator.navigate(
                    InputOTPCodeScreenDestination(
                        authViewModel.phoneNumberText
                    )
                ) {
                    popUpTo(InputPhoneNumberWithApplicationLogoScreenDestination)
                }

                /*
                * Reset field validation status
                * isSuccess to false
                * phone number text to empty string
                */
                authViewModel.onUpdatedValidationStatusChange(false)
                authViewModel.onPhoneNumberChange("")
                authViewModel.changeValidationSuccessScreenStatus()
            }
        }

        InputPhoneNumberWithApplicationLogoContent(navigator, authViewModel, inputPhoneNumberState)
    }
}

@Composable
private fun InputPhoneNumberWithApplicationLogoContent(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel,
    inputPhoneNumberState: InputPhoneNumberState
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
        Spacer(modifier = Modifier.size(128.dp))
        Text(
            text = "Masuk / Daftar sebagai WINGMAN",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.size(24.dp))
        Column {
            OutlinedTextField(
                value = authViewModel.phoneNumberText,
                onValueChange = authViewModel::onPhoneNumberChange,
                label = { Text(text = "Nomor HP (6285111222333)") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Phone
                ),
                isError = inputPhoneNumberState.isError,
                trailingIcon = {
                    if (inputPhoneNumberState.isError) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            tint = MaterialTheme.colors.error,
                            contentDescription = null
                        )
                    }
                }
            )
            if (inputPhoneNumberState.isError) {
                Text(
                    text = inputPhoneNumberState.errorMessage.asString(),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            onClick = {
                authViewModel.validationPhoneNumberTextField()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "LOGIN",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}