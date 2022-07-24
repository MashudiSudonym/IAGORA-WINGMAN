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
    // Navigate to OTP input screen, for back stack to input phone number with application logo screen
    fun navigateToOTPInputScreenBackStackToInputPhoneNumberWithApplicationLogoScreen(
        navigator: DestinationsNavigator,
        phoneNumber: String,
    ) {
        navigator.navigate(
            InputOTPCodeScreenDestination(phoneNumber)
        ) {
            popUpTo(InputPhoneNumberWithApplicationLogoScreenDestination)
        }
    }

    // Navigate to root screen, for back stack to input phone number with application logo screen
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

    // Navigate to wingman detail data form screen,
    // for back stack to input phone number with application logo screen
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

    // Navigate to wingman detail document data form screen,
    // for back stack to registration wingman detail data screen
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

    // Navigate to camera capture wingman user id card
    fun navigateToCameraCaptureWingmanUserIdCardScreen(navigator: DestinationsNavigator) {
        //navigator.navigate(CameraCaptureWingmanUserIdCardDestination)
    }

    // Navigate to camera capture wingman police agreement letter
    fun navigateToCameraCaptureWingmanPoliceAgreementLetterScreen(navigator: DestinationsNavigator) {
        //navigator.navigate(CameraCaptureWingmanPoliceAgreementLetterDestination)
    }
}