package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun AppBarTitleTextCustom(title: String, textColor: Color = Color.Black) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = title,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = textColor)
        Spacer(modifier = Modifier.size(4.dp))
    }
}

@Preview
@Composable
private fun AppBarTitleTextPreview() {
    WINGMANTheme {
        AppBarTitleTextCustom(title = "Your Title")
    }
}