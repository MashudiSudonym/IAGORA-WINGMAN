package com.iagora.wingman.user_profile.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.iagora.wingman.common.presentation.ui.component.IconTextButtonCustom
import com.iagora.wingman.common.presentation.ui.component.MenuTitleCustom
import com.iagora.wingman.user_profile.presentation.UserProfileViewModel
import com.iagora.wingman.user_profile.presentation.event.UserProfileEvent

@Composable
fun WingmanUserProfileMenu(userProfileViewModel: UserProfileViewModel) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        MenuTitleCustom(title = "Pengaturan Akun")
        IconTextButtonCustom(
            buttonTitle = "Rekening Pencairan Dana",
            icon = Icons.Outlined.Payment,
            clickEvent = {},
        )
        IconTextButtonCustom(
            buttonTitle = "Keamanan Akun",
            icon = Icons.Outlined.Shield,
            clickEvent = {},
        )
        Spacer(modifier = Modifier.size(24.dp))
        MenuTitleCustom(title = "Pusat Bantuan")
        IconTextButtonCustom(
            buttonTitle = "FAQ",
            icon = Icons.Outlined.Quiz,
            clickEvent = {},
        )
        IconTextButtonCustom(
            buttonTitle = "IAGORA CARE",
            icon = Icons.Outlined.HeadsetMic,
            clickEvent = {},
        )
        Spacer(modifier = Modifier.size(24.dp))
        MenuTitleCustom(title = "Tentang Kami")
        IconTextButtonCustom(
            buttonTitle = "Syarat & Ketentuan",
            icon = Icons.Outlined.Feed,
            clickEvent = {},
        )
        IconTextButtonCustom(
            buttonTitle = "Tentang Kami",
            icon = Icons.Outlined.SupportAgent,
            clickEvent = {},
        )
        Spacer(modifier = Modifier.size(24.dp))
        IconTextButtonCustom(
            buttonTitle = "Keluar",
            icon = Icons.Outlined.Logout,
            clickEvent = {
                userProfileViewModel.onEvent(UserProfileEvent.Logout)
            },
            fontWeight = FontWeight.SemiBold
        )
    }
}