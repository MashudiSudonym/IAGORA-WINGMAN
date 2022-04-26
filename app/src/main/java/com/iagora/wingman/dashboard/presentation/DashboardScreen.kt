package com.iagora.wingman.dashboard.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagora.wingman.common.presentation.ui.component.BottomNavigationContentWrapper
import com.iagora.wingman.common.presentation.ui.component.CommonPrimaryColorButton
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.iagora.wingman.dashboard.presentation.component.HeaderDashboardContent
import com.iagora.wingman.dashboard.presentation.component.WingmanBalanceCard
import com.iagora.wingman.dashboard.presentation.component.WingmanHelpInformation
import com.iagora.wingman.dashboard.presentation.component.WingmanOrderInformation

@Composable
fun DashBoardScreen() {
    // dashboard content
    BottomNavigationContentWrapper {
        DashboardContent()
    }
}

@Composable
private fun DashboardContent(dashboardViewModel: DashboardViewModel = hiltViewModel()) {
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

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    WINGMANTheme {
        DashboardContent()
    }
}