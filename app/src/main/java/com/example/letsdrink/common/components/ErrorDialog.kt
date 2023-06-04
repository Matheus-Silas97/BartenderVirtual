package com.example.letsdrink.common.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.letsdrink.R

@Composable
fun ErrorDialog(errorMsg: String?, onDismiss: () -> Unit) {
    AlertDialog(
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