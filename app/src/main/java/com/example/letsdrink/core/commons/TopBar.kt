package com.example.letsdrink.core.commons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letsdrink.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String = "", showNavigationIcon: Boolean = true, onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back button")
                }
            }
        },

//        backgroundColor = MaterialTheme.colorScheme.primary,
//        contentColor = Color.White,
//        elevation = 10.dp
    )
}