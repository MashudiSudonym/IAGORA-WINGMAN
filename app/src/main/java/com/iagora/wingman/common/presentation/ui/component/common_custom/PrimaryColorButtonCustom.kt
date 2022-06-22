package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryColorButtonCustom(
    isEnable: Boolean = true,
    clickEvent: () -> Unit,
    buttonTitle: String,
) {
    Button(
        enabled = isEnable,
        onClick = clickEvent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonTitle,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
private fun PrimaryColorButtonCustom() {
    PrimaryColorButtonCustom(clickEvent = { /*TODO*/ }, buttonTitle = "Default Button")
}