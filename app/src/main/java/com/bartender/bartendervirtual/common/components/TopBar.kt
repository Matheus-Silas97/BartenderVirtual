package com.bartender.bartendervirtual.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String = "", showNavigationIcon: Boolean = false, onBackPressed: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(text = title, maxLines = 1)
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back button")
                }
            }
        }
    )
}