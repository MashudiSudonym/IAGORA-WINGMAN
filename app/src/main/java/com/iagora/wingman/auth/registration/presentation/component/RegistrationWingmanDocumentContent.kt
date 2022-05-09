package com.iagora.wingman.auth.registration.presentation.component

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.iagora.wingman.R
import com.iagora.wingman.auth.registration.presentation.RegistrationWingmanDocumentDataViewModel
import com.iagora.wingman.auth.registration.presentation.event.WingmanDocumentDataEvent
import com.iagora.wingman.auth.registration.presentation.state.RegistrationWingmanDocumentDataState
import com.iagora.wingman.common.presentation.ui.component.AppBarTitleTextWithBackArrow
import com.iagora.wingman.common.presentation.ui.component.CommonPrimaryColorButton
import com.iagora.wingman.common.presentation.ui.component.OutlineTextFieldCustom
import com.iagora.wingman.common.presentation.ui.component.TakePictureButton
import com.iagora.wingman.destinations.CameraCaptureWingmanIdCardDestination
import com.iagora.wingman.destinations.CameraCaptureWingmanPoliceAgreementLetterDestination
import com.iagora.wingman.user_profile.presentation.component.MenuTitle
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegistrationWingmanDocumentContent(
    navigator: DestinationsNavigator,
    registrationWingmanDocumentDataState: RegistrationWingmanDocumentDataState,
    registrationWingmanDocumentDataViewModel: RegistrationWingmanDocumentDataViewModel,
    onImageFile: Uri
) {
    val thisScrollState = rememberScrollState()
    val cameraPermissionState =
        rememberPermissionState(permission = Manifest.permission.CAMERA)
    val emptyImage = Uri.parse("file://dev/null")

    if (onImageFile != emptyImage  || registrationWingmanDocumentDataState.userIdCardImage != emptyImage) {
        registrationWingmanDocumentDataViewModel.onEvent(
            WingmanDocumentDataEvent.UserIdCardImage(
                onImageFile
            )
        )
    }

    if (onImageFile != emptyImage || registrationWingmanDocumentDataState.userPoliceAgreementLetterImage != emptyImage) {
        registrationWingmanDocumentDataViewModel.onEvent(
            WingmanDocumentDataEvent.UserPoliceAgreementLetterImage(onImageFile)
        )
    }

    // main screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AppBarTitleTextWithBackArrow(title = "Lengkapi Dokumen") {
            navigator.navigateUp()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(thisScrollState),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            TakePictureButton(
                onClick = {
                    when (cameraPermissionState.status) {
                        PermissionStatus.Granted -> {
                            navigator.navigate(CameraCaptureWingmanIdCardDestination)
                        }
                        is PermissionStatus.Denied -> {
                            cameraPermissionState.launchPermissionRequest()
                        }
                    }
                },
                title = "Foto KTP",
                icon = Icons.Outlined.Camera,
                subtitle = "Ambil Gambar KTP",
                image = registrationWingmanDocumentDataState.userIdCardImage,
                isError = registrationWingmanDocumentDataState.userIdCardImageFieldError != null,
                errorMessage = registrationWingmanDocumentDataState.userIdCardImageFieldError?.asString()
            )
            Spacer(modifier = Modifier.size(16.dp))
            TakePictureButton(
                onClick = {
                    when (cameraPermissionState.status) {
                        PermissionStatus.Granted -> {
                            navigator.navigate(CameraCaptureWingmanPoliceAgreementLetterDestination)
                        }
                        is PermissionStatus.Denied -> {
                            cameraPermissionState.launchPermissionRequest()
                        }
                    }
                },
                title = "Foto SKCK",
                icon = Icons.Outlined.Camera,
                subtitle = "Ambil Gambar SKCK",
                image = registrationWingmanDocumentDataState.userPoliceAgreementLetterImage,
                isError = registrationWingmanDocumentDataState.userPoliceAgreementLetterImageFieldError != null,
                errorMessage = registrationWingmanDocumentDataState.userPoliceAgreementLetterImageFieldError?.asString()
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(id = R.string.attention_bank_document),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.size(16.dp))
            MenuTitle(title = "Nama Bank")
            Spacer(modifier = Modifier.size(8.dp))
            OutlineTextFieldCustom(
                textValue = registrationWingmanDocumentDataState.bankName,
                textValueChange = {
                    registrationWingmanDocumentDataViewModel.onEvent(
                        WingmanDocumentDataEvent.BankNameFieldChange(
                            it
                        )
                    )
                },
                labelText = "Contoh : Bank BCA",
                keyboardType = KeyboardType.Text,
                keyboardActionOnDone = {
                    this.defaultKeyboardAction(ImeAction.Next)
                },
                isError = registrationWingmanDocumentDataState.bankNameFieldError != null,
                errorMessage = registrationWingmanDocumentDataState.bankNameFieldError?.asString()
            )
            Spacer(modifier = Modifier.size(16.dp))
            MenuTitle(title = "Nomor Rekening Bank")
            Spacer(modifier = Modifier.size(8.dp))
            OutlineTextFieldCustom(
                textValue = registrationWingmanDocumentDataState.bankAccountNumber,
                textValueChange = {
                    registrationWingmanDocumentDataViewModel.onEvent(
                        WingmanDocumentDataEvent.BankAccountNumberFieldChange(
                            it
                        )
                    )
                },
                labelText = "Contoh : 50050055550",
                keyboardType = KeyboardType.Number,
                keyboardActionOnDone = {
                    this.defaultKeyboardAction(ImeAction.Next)
                },
                isError = registrationWingmanDocumentDataState.bankAccountNumberFieldError != null,
                errorMessage = registrationWingmanDocumentDataState.bankAccountNumberFieldError?.asString()
            )
            Spacer(modifier = Modifier.size(16.dp))
            MenuTitle(title = "Nama Pemilik Rekening")
            Spacer(modifier = Modifier.size(8.dp))
            OutlineTextFieldCustom(
                textValue = registrationWingmanDocumentDataState.bankAccountUserName,
                textValueChange = {
                    registrationWingmanDocumentDataViewModel.onEvent(
                        WingmanDocumentDataEvent.BankAccountUserNameFieldChange(
                            it
                        )
                    )
                },
                labelText = "Contoh : Admin Yandi",
                keyboardType = KeyboardType.Text,
                keyboardActionOnDone = {
                    registrationWingmanDocumentDataViewModel.onEvent(
                        WingmanDocumentDataEvent.Submit
                    )
                },
                isError = registrationWingmanDocumentDataState.bankAccountUserNameFieldError != null,
                errorMessage = registrationWingmanDocumentDataState.bankAccountUserNameFieldError?.asString()
            )
            Spacer(modifier = Modifier.size(24.dp))
            CommonPrimaryColorButton(clickEvent = {
                registrationWingmanDocumentDataViewModel.onEvent(
                    WingmanDocumentDataEvent.Submit
                )
            }, buttonTitle = "Kirim Data")
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}