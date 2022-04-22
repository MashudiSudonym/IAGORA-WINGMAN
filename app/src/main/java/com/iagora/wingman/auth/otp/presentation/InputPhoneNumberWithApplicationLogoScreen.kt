package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun InputPhoneNumberWithApplicationLogoScreen() {
    Box {
        FullScreenLoadingIndicator()
        InputPhoneNumberWithApplicationLogoContent()
    }
}

@Composable
fun InputPhoneNumberWithApplicationLogoContent() {
    // googling how to text field value work!
    var phoneNumberText by rememberSaveable {
        mutableStateOf("")
    }

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
        Spacer(modifier = Modifier.size(128.dp))
        OutlinedTextField(
            value = phoneNumberText,
            onValueChange = { phoneNumberText = it },
            label = { Text(text = "Nomor HP (+6285xxxxxxxxx)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "LOGIN",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.size(128.dp))
        Text(
            text = "Belum ada akun ?",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Daftar Sebagai WINGMAN",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputPhoneNumberWithApplicationLogoScreenPreview() {
    WINGMANTheme {
        InputPhoneNumberWithApplicationLogoScreen()
    }
}