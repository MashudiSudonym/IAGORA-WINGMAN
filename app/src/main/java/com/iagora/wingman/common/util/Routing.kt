package com.iagora.wingman.common.util

import android.net.Uri
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.iagora.wingman.destinations.*
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Suppress("OPT_IN_IS_NOT_ENABLED")
@ExperimentalCoroutinesApi
@OptIn(ExperimentalPermissionsApi::class)
object Routing {
    fun navigateToRootScreen(navigator: DestinationsNavigator) {
        navigator.navigate(RootScreenDestination) {
            popUpTo(
                InputPhoneNumberWithApplicationLogoScreenDestination
            ) {
                inclusive = true
            }
        }
    }

    fun navigateToWingmanDetailDataFormScreen(navigator: DestinationsNavigator) {
        navigator.navigate(RegistrationWingmanDetailDataScreenDestination) {
            popUpTo(
                InputPhoneNumberWithApplicationLogoScreenDestination
            ) {
                inclusive = true
            }
        }
    }

    fun navigateToWingmanDetailDocumentDataFormScreen(
        navigator: DestinationsNavigator,
        imageUserIdCard: Uri,
        imageUserPoliceAgreementLetter: Uri,
    ) {
        navigator.navigate(RegistrationWingmanDocumentDataScreenDestination(
            imageUserIdCard = imageUserIdCard,
            imageUserPoliceAgreementLetter = imageUserPoliceAgreementLetter)) {
            popUpTo(RegistrationWingmanDetailDataScreenDestination)
        }
    }

    fun navigateToCameraCaptureWingmanUserIdCardDestination(navigator: DestinationsNavigator) {
        navigator.navigate(CameraCaptureWingmanUserIdCardDestination)
    }

    fun navigateToCameraCaptureWingmanPoliceAgreementLetterDestination(navigator: DestinationsNavigator) {
        navigator.navigate(CameraCaptureWingmanPoliceAgreementLetterDestination)
    }
}