package com.iagora.wingman.user_profile.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.CustomIconTextButton
import com.iagora.wingman.common.presentation.ui.component.FullScreenLoadingIndicator
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.iagora.wingman.destinations.InputPhoneNumberWithApplicationLogoScreenDestination
import com.iagora.wingman.destinations.RootScreenDestination
import com.iagora.wingman.user_profile.presentation.UserProfileViewModel
import com.iagora.wingman.user_profile.presentation.event.UserProfileEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlin.math.log

@Composable
fun WingmanUserProfileMenu(userProfileViewModel: UserProfileViewModel) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        MenuTitle(title = "Pengaturan Akun")
        CustomIconTextButton(
            buttonTitle = "Rekening Pencairan Dana",
            icon = Icons.Outlined.Payment,
            clickEvent = {},
        )
        CustomIconTextButton(
            buttonTitle = "Keamanan Akun",
            icon = Icons.Outlined.Shield,
            clickEvent = {},
        )
        Spacer(modifier = Modifier.size(24.dp))
        MenuTitle(title = "Pusat Bantuan")
        CustomIconTextButton(
            buttonTitle = "FAQ",
            icon = Icons.Outlined.Quiz,
            clickEvent = {},
        )
        CustomIconTextButton(
            buttonTitle = "IAGORA CARE",
            icon = Icons.Outlined.HeadsetMic,
            clickEvent = {},
        )
        Spacer(modifier = Modifier.size(24.dp))
        MenuTitle(title = "Tentang Kami")
        CustomIconTextButton(
            buttonTitle = "Syarat & Ketentuan",
            icon = Icons.Outlined.Feed,
            clickEvent = {},
        )
        CustomIconTextButton(
            buttonTitle = "Tentang Kami",
            icon = Icons.Outlined.SupportAgent,
            clickEvent = {},
        )
        Spacer(modifier = Modifier.size(24.dp))
        CustomIconTextButton(
            buttonTitle = "Keluar",
            icon = Icons.Outlined.Logout,
            clickEvent = {
                userProfileViewModel.onEvent(UserProfileEvent.Logout)
            },
            fontWeight = FontWeight.SemiBold
        )
    }
}