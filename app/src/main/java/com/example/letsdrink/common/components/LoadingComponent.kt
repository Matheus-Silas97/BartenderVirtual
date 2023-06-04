package com.example.letsdrink.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.letsdrink.R.raw

@Composable
fun LoadingComponent() {
    val composition by rememberLottieComposition(RawRes(raw.loading_animation))
    LottieAnimation(
        composition,
        modifier = Modifier.size(50.dp),
        contentScale = ContentScale.FillHeight,
        iterations = LottieConstants.IterateForever
    )

}