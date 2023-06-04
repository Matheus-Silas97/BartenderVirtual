package com.example.letsdrink.common.commons_custom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.letsdrink.common.components.LoadingComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCustom(
    titlePage: String = "",
    showNavigationIcon: Boolean = false,
    onBackPressedEvent: () -> Unit = {},
    isLoading: Boolean = false,
    contentComponent: @Composable (modifier: Modifier) -> Unit
) {

    Scaffold(
        topBar = {
            TopBar(
                title = titlePage,
                showNavigationIcon = showNavigationIcon,
                onBackPressed = { onBackPressedEvent() })
        }, content = {
            
            if (isLoading) {
                LoadingComponent()
            }

            contentComponent(
                modifier = Modifier
                    .padding(it)
            )
        })
}