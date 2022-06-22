package com.iagora.wingman.user_profile.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.iagora.wingman.common.presentation.ui.component.IconTextButtonCustom
import com.iagora.wingman.common.presentation.ui.component.MenuTitleCustom
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer24Dp
import com.iagora.wingman.user_profile.presentation.event.UserProfileEvent
import com.iagora.wingman.user_profile.presentation.view_model.UserProfileViewModel

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
        StandardSpacer24Dp()
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
        StandardSpacer24Dp()
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
        StandardSpacer24Dp()
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