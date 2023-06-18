package com.bartender.bartendervirtual.common.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.bartender.bartendervirtual.R

@Composable
fun ErrorDialog(errorMsg: String?, modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { },
        title = { Text(text = stringResource(R.string.error)) },
        text = {
            Text(
                text = errorMsg
                    ?: stringResource(R.string.an_unexpected_error_has_occurred)
            )
        },
        confirmButton = {
            Button(
                onClick = { onDismiss.invoke() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(text = stringResource(R.string.ok))
            }
        })
}