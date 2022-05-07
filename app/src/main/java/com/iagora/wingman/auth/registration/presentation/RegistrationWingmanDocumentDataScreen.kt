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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.iagora.wingman.auth.registration.presentation.component.RegistrationWingmanDocumentContent
import com.iagora.wingman.auth.registration.presentation.event.ValidationEvent
import com.iagora.wingman.destinations.RegistrationWingmanDetailDataScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.io.File

@ExperimentalCoroutinesApi
@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalPermissionsApi::class)
@Destination
@Composable
fun RegistrationWingmanDocumentDataScreen(
    navigator: DestinationsNavigator,
    registrationWingmanDocumentDataViewModel: RegistrationWingmanDocumentDataViewModel = hiltViewModel(),
    onImageFile: Uri
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val registrationWingmanDocumentDataState by registrationWingmanDocumentDataViewModel.registrationWingmanDocumentDataState.collectAsState()
    val registrationWingmanDocumentDataEvent =
        registrationWingmanDocumentDataViewModel.registrationWingmanDocumentDateEvents

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        LaunchedEffect(key1 = context) {
            registrationWingmanDocumentDataEvent.collect { event ->
                when (event) {
                    ValidationEvent.Success -> {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()

                        // open Dashboard Page
                        navigator.navigate(RootScreenDestination) {
                            popUpTo(RegistrationWingmanDetailDataScreenDestination) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }

        // this screen content
        RegistrationWingmanDocumentContent(
            navigator,
            registrationWingmanDocumentDataState,
            registrationWingmanDocumentDataViewModel,
            onImageFile
        )
    }
}