package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun AppBarTitleTextWithBackArrow(
    title: String,
    textColor: Color = Color.Black,
    iconColor: Color = Color.Black,
    onBackPress: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(4.dp))
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back to last screen",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onBackPress() },
            tint = iconColor
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
        )
        Spacer(modifier = Modifier.size(4.dp))
    }
}

@Preview
@Composable
private fun AppBarTitleTextWithBackArrowPreview() {
    WINGMANTheme {
        AppBarTitleTextWithBackArrow(title = stringResource(id = R.string.app_name)) {}
    }
}