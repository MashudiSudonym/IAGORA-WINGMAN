package com.iagora.wingman.auth.registration.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.iagora.wingman.auth.registration.presentation.component.RegistrationWingmanDetailDataContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RegistrationWingmanDetailDataScreen(navigator: DestinationsNavigator) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        // default registration detail data content screen
        RegistrationWingmanDetailDataContent()
    }
}