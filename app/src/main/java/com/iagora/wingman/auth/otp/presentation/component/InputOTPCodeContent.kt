package com.iagora.wingman.auth.otp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.state.CountDownState
import com.iagora.wingman.auth.otp.presentation.state.InputOTPCodeState
import com.iagora.wingman.auth.otp.presentation.view_model.AuthVerifyOTPCodeViewModel
import com.iagora.wingman.common.presentation.ui.component.PrimaryColorButtonCustom
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer16Dp
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer64Dp
import com.iagora.wingman.common.util.Constants
import com.mukesh.OTP_VIEW_TYPE_UNDERLINE
import com.mukesh.OtpView


@Composable
fun InputOTPCodeContent(
    phoneNumber: String,
    authVerifyOTPCodeViewModel: AuthVerifyOTPCodeViewModel,
    inputOTPCodeState: InputOTPCodeState,
    countDownState: CountDownState,
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
        StandardSpacer16Dp()
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.SemiBold
        )
        StandardSpacer64Dp()
        Text(
            text = "Masukkan Kode OTP",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold
        )
        StandardSpacer16Dp()
        Text(
            text = "Kami telah mengirimkan kode OTP pada nomor WA $phoneNumber silahkan dicek",
            textAlign = TextAlign.Center
        )
        StandardSpacer16Dp()
        OtpView(
            onOtpTextChange = authVerifyOTPCodeViewModel::onOTPCodeChange,
            otpText = authVerifyOTPCodeViewModel.otpCodeText,
            type = OTP_VIEW_TYPE_UNDERLINE,
            password = true,
            containerSize = 48.dp,
            passwordChar = "•",
            otpCount = 6,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        )
        Spacer(modifier = Modifier.size(16.dp))
        PrimaryColorButtonCustom(
            clickEvent = {
                focusManager.clearFocus()
                authVerifyOTPCodeViewModel.validationOTPCodeTextFieldAndSendOTPVerification(
                    phoneNumber
                )
            },
            buttonTitle = "KIRIM OTP",
            isEnable = !inputOTPCodeState.isLoading,
        )
        StandardSpacer16Dp()
        // If count down timer it's end, show up this button
        if (countDownState.isCountDownStop) {
            TextButton(onClick = { authVerifyOTPCodeViewModel.requestNewOTPCode(phoneNumber) }) {
                Text(
                    text = "MINTA OTP",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary,
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