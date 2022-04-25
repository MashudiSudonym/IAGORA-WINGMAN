package com.iagora.wingman.dashboard.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.iagora.wingman.R

@Composable
fun WingmanHelpInformation() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_background_home),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.size(48.dp))
                Text(
                    text = "Penjelasan lengkap",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Text(
                    text = "memproses pesanan",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = MaterialTheme.colors.primary
                    )
                ) {
                    Text(
                        text = "Klik disini!",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}