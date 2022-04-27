package com.iagora.wingman.user_profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapper
import com.iagora.wingman.common.presentation.ui.component.CustomIconTextButton
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.iagora.wingman.user_profile.presentation.component.HeaderUserProfileContent
import com.iagora.wingman.user_profile.presentation.component.MenuTitle
import com.iagora.wingman.user_profile.presentation.component.WingmanUserProfileMenu

@Composable
fun UserProfileScreen() {
    BottomNavigationContentWrapper {
        UserProfileContent()
    }
}

@Composable
private fun UserProfileContent() {
    val thisScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(thisScrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        HeaderUserProfileContent()
        Spacer(modifier = Modifier.size(48.dp))
        WingmanUserProfileMenu()
    }
}