package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun MenuTitleCustom(title: String, textColor: Color = Color.Black) {
    Text(
        text = title,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.SemiBold,
        color = textColor
    )
}

@Preview
@Composable
private fun MenuTitlePreview() {
    WINGMANTheme {
        MenuTitleCustom("Menu Title")
    }
}