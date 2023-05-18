package com.example.letsdrink.core.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCustom(
    titlePage: String = "",
    showNavigationIcon: Boolean = false,
    onBackPressedEvent: () -> Unit,
    contentComponent: @Composable (modifier: Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = titlePage,
                showNavigationIcon = showNavigationIcon,
                onBackPressed = { onBackPressedEvent() })
        }, content = {
            contentComponent(
                modifier = Modifier
                    .padding(it)
            )
        })
}