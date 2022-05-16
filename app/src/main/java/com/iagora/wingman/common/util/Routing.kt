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
    fun navigateToRootScreenBackStackToInputPhoneNumberWithApplicationLogoScreen(
        navigator: DestinationsNavigator,
        inclusiveStatus: Boolean = false,
    ) {
        navigator.navigate(RootScreenDestination) {
            popUpTo(
                InputPhoneNumberWithApplicationLogoScreenDestination
            ) {
                inclusive = inclusiveStatus
            }
        }
    }

    fun navigateToWingmanDetailDataFormScreenBackStackToInputPhoneNumberWithApplicationLogoScreen(
        navigator: DestinationsNavigator,
        inclusiveStatus: Boolean = false,
    ) {
        navigator.navigate(RegistrationWingmanDetailDataScreenDestination) {
            popUpTo(
                InputPhoneNumberWithApplicationLogoScreenDestination
            ) {
                inclusive = inclusiveStatus
            }
        }
    }

    fun navigateToWingmanDetailDocumentDataFormScreenBackStackToRegistrationWingmanDetailDataScreen(
        navigator: DestinationsNavigator,
        imageUserIdCard: Uri,
        imageUserPoliceAgreementLetter: Uri,
        inclusiveStatus: Boolean = false,
    ) {
        navigator.navigate(RegistrationWingmanDocumentDataScreenDestination(
            imageUserIdCard = imageUserIdCard,
            imageUserPoliceAgreementLetter = imageUserPoliceAgreementLetter)) {
            popUpTo(RegistrationWingmanDetailDataScreenDestination) {
                inclusive = inclusiveStatus
            }
        }
    }

    fun navigateToCameraCaptureWingmanUserIdCardDestination(navigator: DestinationsNavigator) {
        navigator.navigate(CameraCaptureWingmanUserIdCardDestination)
    }

    fun navigateToCameraCaptureWingmanPoliceAgreementLetterDestination(navigator: DestinationsNavigator) {
        navigator.navigate(CameraCaptureWingmanPoliceAgreementLetterDestination)
    }
}