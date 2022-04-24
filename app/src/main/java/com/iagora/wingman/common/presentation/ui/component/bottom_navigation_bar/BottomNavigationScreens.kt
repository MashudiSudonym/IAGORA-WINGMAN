package com.iagora.wingman.common.presentation.ui.component.bottom_navigation_bar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.common.util.Constants

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resId: Int,
    val icon: @Composable () -> Unit
) {
    object Home : BottomNavigationScreens(
        route = Constants.HOME,
        resId = R.string.home_title,
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_home),
                modifier = Modifier.size(18.dp),
                contentDescription = null
            )
        }
    )

    object CustomerCare : BottomNavigationScreens(
        route = Constants.CUSTOMER_CARE,
        resId = R.string.customer_care_title,
        icon = { Icon(imageVector = Icons.Outlined.SupportAgent, contentDescription = null) }
    )

    object Profile : BottomNavigationScreens(
        route = Constants.PROFILE,
        resId = R.string.profile_title,
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_user),
                modifier = Modifier.size(18.dp),
                contentDescription = null
            )
        }
    )
}