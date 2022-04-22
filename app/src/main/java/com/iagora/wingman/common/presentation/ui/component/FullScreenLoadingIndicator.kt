package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun FullScreenLoadingIndicator() {
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(Modifier.size(8.dp))
        Text(text = "Loading", style = MaterialTheme.typography.overline)
    }
}

@Preview(showBackground = true)
@Composable
fun FullScreenLoadingIndicatorPreview() {
    WINGMANTheme {
        FullScreenLoadingIndicator()
    }
}