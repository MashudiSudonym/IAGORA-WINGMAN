package com.iagora.wingman.user_profile.presentation.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun MenuTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview
@Composable
private fun MenuTitlePreview() {
    WINGMANTheme {
        MenuTitle("Menu Title")
    }
}