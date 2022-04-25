package com.iagora.wingman.dashboard.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WingmanOrderInformation() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier.height(128.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isSystemInDarkTheme()) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_shopping_bag_white),
                        contentDescription = null
                    )
                } else {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_shopping_bag),
                        contentDescription = null
                    )
                }
                Text(text = "{1} Pesanan Baru")
            }
        }
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier.height(128.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
                Text(text = "Pesanan Selesai")
            }
        }
    }
}