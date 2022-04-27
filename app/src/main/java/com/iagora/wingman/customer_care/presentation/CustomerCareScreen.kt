package com.iagora.wingman.customer_care.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapper
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.customer_care.presentation.state.AccessTokenState

@Composable
fun CustomerCareScreen(customerCareViewModel: CustomerCareViewModel = hiltViewModel()) {
    val accessTokenState by customerCareViewModel.accessTokenState.collectAsState()

    BottomNavigationContentWrapper {
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
            accessTokenState.isLoading -> FullScreenLoadingIndicator()
            accessTokenState.isError -> Text(text = accessTokenState.accessToken)
            else -> Text(text = accessTokenState.accessToken)
        }
    }
}