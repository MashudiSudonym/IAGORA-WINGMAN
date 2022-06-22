package com.iagora.wingman.dashboard.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.PrimaryColorButtonCustom
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer16Dp
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer24Dp
import com.iagora.wingman.dashboard.presentation.view_model.DashboardViewModel

@Composable
fun DashboardContent(dashboardViewModel: DashboardViewModel = hiltViewModel()) {
    val greetingMessageState by dashboardViewModel.greetingMessageState.collectAsState()
    val thisScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(thisScrollState)
    ) {
        HeaderDashboardContent(greetingMessageState)
        WingmanBalanceCard()
        StandardSpacer16Dp()
        WingmanOrderInformation()
        StandardSpacer24Dp()
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            PrimaryColorButtonCustom(
                clickEvent = { /*TODO*/ },
                buttonTitle = "TAMBAH PRODUK",
            )
            StandardSpacer24Dp()
            Text(
                text = "Langkah-langkah memproses pesanan",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
            )
        }
        WingmanHelpInformation()
        StandardSpacer16Dp()
    }
}