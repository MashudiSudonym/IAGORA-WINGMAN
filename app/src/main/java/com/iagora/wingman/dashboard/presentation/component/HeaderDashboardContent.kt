package com.iagora.wingman.dashboard.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import com.iagora.wingman.dashboard.presentation.state.GreetingMessageState

@Composable
fun HeaderDashboardContent(greetingMessageState: GreetingMessageState) {
    val checkedState = remember { mutableStateOf(true) }

    Box {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_background_home),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Selamat ${greetingMessageState.greeting}, {Jack}",
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.size(4.dp))
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "{4.8} / 5",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Layanan sedang {aktif}",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(2.dp))
                Switch(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.LightGray,
                        checkedTrackColor = Color.White,
                        uncheckedTrackColor = Color.Gray
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun HeaderDashboardContentPreview() {
    WINGMANTheme {
        HeaderDashboardContent(greetingMessageState = GreetingMessageState())
    }
}