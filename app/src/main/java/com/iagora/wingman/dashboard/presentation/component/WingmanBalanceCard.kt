package com.iagora.wingman.dashboard.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.common.presentation.ui.component.spacer.StandardSpacer16Dp
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WingmanBalanceCard() {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(75.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StandardSpacer16Dp()
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_wallet),
                contentDescription = null
            )
            StandardSpacer16Dp()
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Pendapatan hari ini", style = MaterialTheme.typography.caption)
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = "Rp.{75.000}",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right_rounded),
                contentDescription = null
            )
            StandardSpacer16Dp()
        }
    }
}

@Preview
@Composable
private fun WingmanBalanceCardPreview() {
    WINGMANTheme {
        WingmanBalanceCard()
    }
}