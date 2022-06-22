package com.iagora.wingman.user_profile.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer16Dp
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacerCustomDp
import com.iagora.wingman.user_profile.presentation.view_model.UserProfileViewModel

@Composable
fun UserProfileContent(userProfileViewModel: UserProfileViewModel) {
    val thisScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(thisScrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        StandardSpacer16Dp()
        HeaderUserProfileContent()
        StandardSpacerCustomDp(48.dp)
        WingmanUserProfileMenu(userProfileViewModel)
    }
}