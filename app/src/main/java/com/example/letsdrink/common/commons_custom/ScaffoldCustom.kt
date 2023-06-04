package com.example.letsdrink.common.commons_custom

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.letsdrink.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCustom(
    titlePage: String = "",
    showNavigationIcon: Boolean = false,
    onBackPressedEvent: () -> Unit = {},
    isLoading: Boolean = false,
    contentComponent: @Composable (modifier: Modifier) -> Unit
) {

    val composition by rememberLottieComposition(RawRes(R.raw.loading_animation))

    Scaffold(
        topBar = {
            TopBar(
                title = titlePage,
                showNavigationIcon = showNavigationIcon,
                onBackPressed = { onBackPressedEvent() })
        }, content = {
            
            if (isLoading) {
                LottieAnimation(
                    composition,
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.FillHeight,
                    iterations = LottieConstants.IterateForever
                )
            }

            contentComponent(
                modifier = Modifier
                    .padding(it)
            )
        })
}