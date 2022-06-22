package com.iagora.wingman.auth.otp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.AuthRequestOTPCodeViewModel
import com.iagora.wingman.auth.otp.presentation.event.InputPhoneNumberDataEvent
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.common.presentation.ui.component.OutlineTextFieldCustom
import com.iagora.wingman.common.presentation.ui.component.PrimaryColorButtonCustom

@Composable
fun InputPhoneNumberWithApplicationLogoContent(
    authRequestOTPCodeViewModel: AuthRequestOTPCodeViewModel,
    inputPhoneNumberState: InputPhoneNumberState,
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
        PrimaryColorButtonCustom(
            clickEvent = {
                focusManager.clearFocus()
                authRequestOTPCodeViewModel.onInputFieldEvent(InputPhoneNumberDataEvent.Submit)
            },
            buttonTitle = "LOGIN",
            isEnable = !inputPhoneNumberState.isLoading
        )
    }
}