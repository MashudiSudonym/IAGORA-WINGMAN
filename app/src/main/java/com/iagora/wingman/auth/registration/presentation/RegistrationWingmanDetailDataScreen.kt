package com.iagora.wingman.auth.registration.presentation

import android.net.Uri
import android.widget.Toast
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
import com.iagora.wingman.auth.registration.presentation.component.RegistrationWingmanDetailDataContent
import com.iagora.wingman.common.presentation.event.FormValidationEvent
import com.iagora.wingman.destinations.RegistrationWingmanDetailDataScreenDestination
import com.iagora.wingman.destinations.RegistrationWingmanDocumentDataScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Destination
@Composable
fun RegistrationWingmanDetailDataScreen(
    navigator: DestinationsNavigator,
    registrationWingmanDetailDataViewModel: RegistrationWingmanDetailDataViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val registrationWingmanDetailDataState by registrationWingmanDetailDataViewModel.registrationWingmanDetailDataState.collectAsState()
    val registrationWingmanDetailDataEvents =
        registrationWingmanDetailDataViewModel.registrationWingmanDetailDataEvents

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        LaunchedEffect(key1 = context) {
            registrationWingmanDetailDataEvents.collect { event ->
                when (event) {
                    FormValidationEvent.Success -> {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()

                        // open next registration step screen
                        navigator.navigate(RegistrationWingmanDocumentDataScreenDestination(onImageFile = Uri.parse("file://dev/null"))) {
                            popUpTo(RegistrationWingmanDetailDataScreenDestination) {
                                inclusive = false
                            }
                        }
                    }
                }
            }
        }

        // default registration detail data content screen
        RegistrationWingmanDetailDataContent(registrationWingmanDetailDataState, registrationWingmanDetailDataViewModel)
    }
}