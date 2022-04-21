package com.iagora.wingman.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iagora.wingman.common.presentation.ui.theme.WINGMANTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WINGMANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column {
        Text(text = "H1 $name!", style = MaterialTheme.typography.h1)
        Text(text = "H2 $name!", style = MaterialTheme.typography.h2)
        Text(text = "H3 $name!", style = MaterialTheme.typography.h3)
        Text(text = "H4 $name!", style = MaterialTheme.typography.h4)
        Text(text = "H5 $name!", style = MaterialTheme.typography.h5)
        Text(text = "H6 $name!", style = MaterialTheme.typography.h6)
        Text(text = "Body1 $name!", style = MaterialTheme.typography.body1)
        Text(text = "Body2 $name!", style = MaterialTheme.typography.body2)
        Text(text = "Subtitle1 $name!", style = MaterialTheme.typography.subtitle1)
        Text(text = "Subtitle2 $name!", style = MaterialTheme.typography.subtitle2)
        Text(text = "Button $name!", style = MaterialTheme.typography.button)
        Text(text = "Caption $name!", style = MaterialTheme.typography.caption)
        Text(text = "Overline $name!", style = MaterialTheme.typography.overline)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WINGMANTheme {
        Greeting("Android")
    }
}