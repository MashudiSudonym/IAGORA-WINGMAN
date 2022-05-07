package com.iagora.wingman.auth.registration.presentation.component

import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.auth.registration.presentation.RegistrationWingmanDetailDataViewModel
import com.iagora.wingman.auth.registration.presentation.event.WingmanDetailDataEvent
import com.iagora.wingman.auth.registration.presentation.state.RegistrationWingmanDetailDataState
import com.iagora.wingman.common.presentation.ui.component.AppBarTitleText
import com.iagora.wingman.common.presentation.ui.component.CommonPrimaryColorButton
import com.iagora.wingman.common.presentation.ui.component.OutlineTextFieldCustom
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.iagora.wingman.user_profile.presentation.component.MenuTitle
import kotlinx.coroutines.coroutineScope

@Composable
fun RegistrationWingmanDetailDataContent(
    registrationWingmanDetailDataState: RegistrationWingmanDetailDataState,
    registrationWingmanDetailDataViewModel: RegistrationWingmanDetailDataViewModel
) {
    val thisScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AppBarTitleText(title = "Lengkapi Data Diri")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(thisScrollState),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            MenuTitle(title = "Nama Lengkap")
            Spacer(modifier = Modifier.size(4.dp))
            OutlineTextFieldCustom(
                textValue = registrationWingmanDetailDataState.name,
                textValueChange = {
                    registrationWingmanDetailDataViewModel.onEvent(
                        WingmanDetailDataEvent.NameFieldChange(it)
                    )
                },
                isError = registrationWingmanDetailDataState.nameFieldError != null,
                errorMessage = registrationWingmanDetailDataState.nameFieldError?.asString(),
                labelText = "Contoh : Ahmad Admin",
                keyboardType = KeyboardType.Text,
                keyboardActionOnDone = { this.defaultKeyboardAction(ImeAction.Next) }
            )
            Spacer(modifier = Modifier.size(16.dp))
            MenuTitle(title = "Email")
            Spacer(modifier = Modifier.size(4.dp))
            OutlineTextFieldCustom(
                textValue = registrationWingmanDetailDataState.email,
                textValueChange = {
                    registrationWingmanDetailDataViewModel.onEvent(
                        WingmanDetailDataEvent.EmailFieldChange(it)
                    )
                },
                isError = registrationWingmanDetailDataState.emailFieldError != null,
                errorMessage = registrationWingmanDetailDataState.emailFieldError?.asString(),
                labelText = "Contoh : ahmad_admin123@email.com",
                keyboardType = KeyboardType.Email,
                keyboardActionOnDone = {
                    this.defaultKeyboardAction(ImeAction.Next)
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            MenuTitle(title = "Alamat Lengkap")
            Spacer(modifier = Modifier.size(4.dp))
            OutlineTextFieldCustom(
                textValue = registrationWingmanDetailDataState.address,
                textValueChange = {
                    registrationWingmanDetailDataViewModel.onEvent(
                        WingmanDetailDataEvent.AddressFieldChange(it)
                    )
                },
                isError = registrationWingmanDetailDataState.addressFieldError != null,
                errorMessage = registrationWingmanDetailDataState.addressFieldError?.asString(),
                labelText = "Contoh : Jl. Pahlawanku. No.10. Desaku. Kecamatanku. Depan Toko AprilMart",
                keyboardType = KeyboardType.Text,
                keyboardActionOnDone = { this.defaultKeyboardAction(ImeAction.Next) },
                singleLine = false
            )
            Spacer(modifier = Modifier.size(16.dp))
            MenuTitle(title = "Kota Registrasi")
            Spacer(modifier = Modifier.size(4.dp))
            OutlineTextFieldCustom(
                textValue = registrationWingmanDetailDataState.city,
                textValueChange = {
                    registrationWingmanDetailDataViewModel.onEvent(
                        WingmanDetailDataEvent.CityFieldChange(it)
                    )
                },
                isError = registrationWingmanDetailDataState.cityFieldError != null,
                errorMessage = registrationWingmanDetailDataState.cityFieldError?.asString(),
                labelText = "Contoh : Medan",
                keyboardType = KeyboardType.Text,
                keyboardActionOnDone = {
                    registrationWingmanDetailDataViewModel.onEvent(WingmanDetailDataEvent.Submit)
                }
            )
            Spacer(modifier = Modifier.size(24.dp))
            CommonPrimaryColorButton(clickEvent = {
                registrationWingmanDetailDataViewModel.onEvent(
                    WingmanDetailDataEvent.Submit
                )
            }, buttonTitle = "Kirim Data")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationWingmanDetailDataPreview() {
    WINGMANTheme {
        RegistrationWingmanDetailDataContent(
            registrationWingmanDetailDataState = RegistrationWingmanDetailDataState(),
            registrationWingmanDetailDataViewModel = hiltViewModel()
        )
    }
}