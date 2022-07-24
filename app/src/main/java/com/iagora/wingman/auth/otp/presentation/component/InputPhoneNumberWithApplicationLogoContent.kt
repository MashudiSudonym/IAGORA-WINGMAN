package com.iagora.wingman.auth.otp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.iagora.wingman.auth.otp.presentation.event.InputPhoneNumberDataEvent
import com.iagora.wingman.auth.otp.presentation.state.InputPhoneNumberState
import com.iagora.wingman.auth.otp.presentation.view_model.AuthRequestOTPCodeViewModel
import com.iagora.wingman.common.presentation.ui.component.OutlineTextFieldCustom
import com.iagora.wingman.common.presentation.ui.component.PrimaryColorButtonCustom
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer128Dp
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer16Dp
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer24Dp

@Composable
fun InputPhoneNumberWithApplicationLogoContent(
    authRequestOTPCodeViewModel: AuthRequestOTPCodeViewModel,
    inputPhoneNumberState: InputPhoneNumberState,
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.logo_wingman),
                contentDescription = stringResource(R.string.logo_wingman_desc)
            )
            StandardSpacer16Dp()
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.SemiBold
            )
            StandardSpacer128Dp()
            Text(
                text = "Masuk / Daftar sebagai WINGMAN",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Medium
            )
            StandardSpacer24Dp()
            OutlineTextFieldCustom(
                textValue = inputPhoneNumberState.phoneNumber,
                textValueChange = {
                    authRequestOTPCodeViewModel.onInputFieldEvent(
                        InputPhoneNumberDataEvent.PhoneNumberFieldChange(
                            it
                        )
                    )
                },
                enabled = !inputPhoneNumberState.isLoading,
                labelText = "Nomor HP (6285111222333)",
                isError = inputPhoneNumberState.phoneNumberFieldError != null,
                errorMessage = inputPhoneNumberState.phoneNumberFieldError?.asString(),
                keyboardType = KeyboardType.Phone,
                keyboardActionOnDone = {
                    focusManager.clearFocus()
                    authRequestOTPCodeViewModel.onInputFieldEvent(InputPhoneNumberDataEvent.Submit)
                }
            )
            StandardSpacer24Dp()
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
}