package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun CustomIconTextButton(
    buttonTitle: String,
    icon: ImageVector,
    clickEvent: () -> Unit,
    fontWeight: FontWeight? = null,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = clickEvent, modifier = modifier.fillMaxWidth()) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = buttonTitle, style = MaterialTheme.typography.body1, color = Color.Black, fontWeight = fontWeight)
        Spacer(modifier = Modifier.weight(1f))
    }
}