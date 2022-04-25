package com.iagora.wingman.common.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SingleLineOutlineTextFieldCustom(
    textValue: String,
    textValueChange: (String) -> Unit,
    labelText: String,
    isError: Boolean = false,
    errorMessage: String,
    keyboardType: KeyboardType,
    keyboardActionOnDone: (KeyboardActionScope.() -> Unit),
) {
    Column {
        OutlinedTextField(
            value = textValue,
            onValueChange = textValueChange,
            label = { Text(text = labelText) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = keyboardType
            ),
            keyboardActions = KeyboardActions(
                onDone = keyboardActionOnDone
            ),
            isError = isError,
            trailingIcon = {
                if (isError) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        tint = MaterialTheme.colors.error,
                        contentDescription = null
                    )
                }
            }
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }
    }
}