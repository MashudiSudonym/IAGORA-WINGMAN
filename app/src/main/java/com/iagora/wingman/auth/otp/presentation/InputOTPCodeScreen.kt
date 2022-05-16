package com.iagora.wingman.auth.otp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.presentation.component.InputOTPCodeContent
import com.iagora.wingman.auth.otp.presentation.state.CountDownState
import com.iagora.wingman.auth.otp.presentation.state.InputOTPCodeState
import com.iagora.wingman.common.presentation.ui.component.CommonPrimaryColorButton
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.component.OutlineTextFieldCustom
import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.common.util.Routing
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.iagora.wingman.destinations.RegistrationWingmanDetailDataScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Destination
@Composable
fun InputOTPCodeScreen(
    navigator: DestinationsNavigator,
    routing: Routing = Routing,
    phoneNumber: String,
    authVerifyOTPCodeViewModel: AuthVerifyOTPCodeViewModel = hiltViewModel()
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
            inputOTPCodeState.isLoading -> FullScreenLoadingIndicator()
            inputOTPCodeState.isSuccess -> {
                // check if not user complete registration, open registration screen
                if (!inputOTPCodeState.isCompleteRegister) {
                    routing.navigateToWingmanDetailDataFormScreen(navigator)
                } else {
                    // if user complete registration, open dashboard screen
                    routing.navigateToRootScreen(navigator)
                }

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