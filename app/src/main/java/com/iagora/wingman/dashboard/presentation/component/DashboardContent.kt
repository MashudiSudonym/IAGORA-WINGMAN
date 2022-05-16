package com.iagora.wingman.dashboard.presentation.component

import androidx.compose.foundation.layout.*
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
import com.iagora.wingman.common.presentation.ui.component.CommonPrimaryColorButton
import com.iagora.wingman.dashboard.presentation.DashboardViewModel

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
        Spacer(modifier = Modifier.size(16.dp))
        WingmanOrderInformation()
        Spacer(modifier = Modifier.size(24.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            CommonPrimaryColorButton(
                clickEvent = { /*TODO*/ },
                buttonTitle = "TAMBAH PRODUK",
            )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "Langkah-langkah memproses pesanan",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
            )
        }
        WingmanHelpInformation()
        Spacer(modifier = Modifier.size(16.dp))
    }
}