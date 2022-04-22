package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.R
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun InputPhoneNumberScreen(navigator: DestinationsNavigator) {
    InputPhoneNumberContent(navigator)
}

@Composable
private fun InputPhoneNumberContent(navigator: DestinationsNavigator) {
    // googling how to text field value work!
    var phoneNumberText by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
            .navigationBarsWithImePadding()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Back to login"
                )
            }
            Text(text = "Pendaftaran WINGMAN", style = MaterialTheme.typography.h6)
        }
        Spacer(modifier = Modifier.size(256.dp))
        Text(text = "Masukkan Nomor HP", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = phoneNumberText,
            onValueChange = {
                if (it.length <= 13) {
                    phoneNumberText = it
                }
            },
            label = { Text(text = "Nomor HP (6285111222333)") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Phone
            )
        )
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "VERIFIKASI",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}