package com.iagora.wingman.customer_care.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapperCustom
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicatorCustom
import com.iagora.wingman.customer_care.presentation.state.AccessTokenState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CustomerCareScreen(
    navigator: DestinationsNavigator,
    customerCareViewModel: CustomerCareViewModel = hiltViewModel(),
) {
    val accessTokenState by customerCareViewModel.accessTokenState.collectAsState()

    BottomNavigationContentWrapperCustom {
        CustomerScreenContent(accessTokenState)
    }
}

@Composable
private fun CustomerScreenContent(accessTokenState: AccessTokenState) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            accessTokenState.isLoading -> FullScreenLoadingIndicatorCustom()
            accessTokenState.isError -> Text(text = accessTokenState.accessToken)
            else -> Text(text = accessTokenState.accessToken)
        }
    }
}