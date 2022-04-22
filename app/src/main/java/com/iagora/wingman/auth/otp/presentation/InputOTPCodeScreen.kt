package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun InputOTPCodeScreen() {
    Box {
//        FullScreenLoadingIndicator()
        InputOTPCodeContent()
    }
}

@Composable
private fun InputOTPCodeContent() {
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
            text = "Kami telah mengirimkan kode OTP pada nomor WA %d silahkan dicek",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(64.dp))
        Text(
            text = "_ _ _ _ _ _",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(64.dp))
        Text(
            text = "BELUM MENDAPATKAN TOKEN ? MINTA KEMBALI DALAM %d DETIK",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary,
        )
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "MINTA OTP",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputOTPCodeScreenPreview() {
    WINGMANTheme {
        InputOTPCodeScreen()
    }
}