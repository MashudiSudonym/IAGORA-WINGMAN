package com.iagora.wingman.auth.otp.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.auth.otp.presentation.component.InputOTPCodeContent
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicatorCustom
import com.iagora.wingman.common.util.Routing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Destination
@Composable
fun InputOTPCodeScreen(
    navigator: DestinationsNavigator,
    routing: Routing = Routing,
    phoneNumber: String,
    authVerifyOTPCodeViewModel: AuthVerifyOTPCodeViewModel = hiltViewModel(),
) {
    val inputOTPCodeState by authVerifyOTPCodeViewModel.inputOTPCodeState.collectAsState()
    val countDownState by authVerifyOTPCodeViewModel.countDownState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    // running count down timer
    authVerifyOTPCodeViewModel.countDownTimer()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        // navigate to registration screen after success with otp code verification
        when {
            inputOTPCodeState.isError -> LaunchedEffect(scaffoldState) {
                authVerifyOTPCodeViewModel.inputOTPCodeErrors.collect { data ->
                    scaffoldState.snackbarHostState.showSnackbar(data.asString(context))
                }
            }
            inputOTPCodeState.isLoading -> FullScreenLoadingIndicatorCustom()
            inputOTPCodeState.isSuccess -> {
                // check if not user complete registration, open registration screen
//                if (!inputOTPCodeState.isCompleteRegister) {
//                    routing.navigateToWingmanDetailDataFormScreenBackStackToInputPhoneNumberWithApplicationLogoScreen(navigator, true)
//                } else {
//                    // if user complete registration, open dashboard screen
//                    routing.navigateToRootScreenBackStackToInputPhoneNumberWithApplicationLogoScreen(navigator, true)
//                }
                routing.navigateToRootScreenBackStackToInputPhoneNumberWithApplicationLogoScreen(
                    navigator,
                    true)

                /*
                * Reset field validation status
                * isSuccess to false
                */
                authVerifyOTPCodeViewModel.onUpdatedValidationStatusChange(false)
                authVerifyOTPCodeViewModel.changeValidationSuccessScreenStatus()
            }
        }

        InputOTPCodeContent(
            phoneNumber,
            authVerifyOTPCodeViewModel,
            inputOTPCodeState,
            countDownState
        )
    }
}