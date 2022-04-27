package com.iagora.wingman.user_profile.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme

@Composable
fun HeaderUserProfileContent() {
    // TODO: change image using Coil Image if user profile endpoint it's ready
    Image(
        modifier = Modifier
            .shadow(elevation = 4.dp, shape = CircleShape, clip = true)
            .size(100.dp)
            .background(color = Color.White)
            .clip(CircleShape)
            .border(width = 4.dp, color = Color.White, shape = CircleShape),
        painter = painterResource(id = R.drawable.logo_wingman),
        contentDescription = "Image User Profile"
    )
    Spacer(modifier = Modifier.size(16.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "{Jack The Wingman}",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "{4.8}",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun HeaderUserProfilePreview() {
    WINGMANTheme {
        HeaderUserProfileContent()
    }
}