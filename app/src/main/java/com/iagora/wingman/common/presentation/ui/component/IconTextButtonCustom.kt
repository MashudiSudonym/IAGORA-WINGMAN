package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconTextButtonCustom(
    modifier: Modifier = Modifier,
    buttonTitle: String,
    icon: ImageVector,
    clickEvent: () -> Unit,
    fontWeight: FontWeight? = null,
    textColor: Color = Color.Black,
    iconColor: Color = Color.Black,
) {
    TextButton(onClick = clickEvent, modifier = modifier.fillMaxWidth()) {
        Icon(imageVector = icon, contentDescription = null, tint = iconColor)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = buttonTitle,
            style = MaterialTheme.typography.body1,
            color = textColor,
            fontWeight = fontWeight)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun CustomIconTextButtonPreview() {
    IconTextButtonCustom(buttonTitle = "Button Title",
        icon = Icons.Default.Android,
        clickEvent = { /*TODO*/ })
}