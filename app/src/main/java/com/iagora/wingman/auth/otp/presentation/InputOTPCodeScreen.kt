package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.state.CountDownState
import com.iagora.wingman.auth.otp.presentation.state.InputOTPCodeState
import com.iagora.wingman.common.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun InputOTPCodeScreen(
    navigator: DestinationsNavigator,
    phoneNumber: String,
    authVerifyOTPCodeViewModel: AuthVerifyOTPCodeViewModel = hiltViewModel()
) {
    val inputOTPCodeState by authVerifyOTPCodeViewModel.inputOTPCodeState.collectAsState()
    val countDownState by authVerifyOTPCodeViewModel.countDownState.collectAsState()

    // running count down timer
    authVerifyOTPCodeViewModel.countDownTimer()

    InputOTPCodeContent(
        navigator,
        phoneNumber,
        authVerifyOTPCodeViewModel,
        inputOTPCodeState,
        countDownState
    )
}

@Composable
private fun InputOTPCodeContent(
    navigator: DestinationsNavigator,
    phoneNumber: String,
    authVerifyOTPCodeViewModel: AuthVerifyOTPCodeViewModel,
    inputOTPCodeState: InputOTPCodeState,
    countDownState: CountDownState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
        Text(
            text = "_ _ _ _ _ _",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(64.dp))
        // If count down timer it's end, show up this button
        if (countDownState.isCountDownStop) {
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "MINTA OTP",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold
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