package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec.Progress
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bartender.bartendervirtual.R.raw

@Composable
fun LoadingComponent(
    text: String = "",
    isLoading: Boolean,
    finishListener: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
    ) {

        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            TextTitle(text = text, modifier = Modifier.padding(end = 50.dp, start = 12.dp))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            LoadingLottieView(isLoading = isLoading, finishListener = finishListener)
        }
    }
}

@Composable
fun LoadingLottieView(isLoading: Boolean, finishListener: @Composable () -> Unit) {
    val composition by rememberLottieComposition(RawRes(raw.ic_loading))
    val progress by animateLottieCompositionAsState(
        composition,
        clipSpec = Progress(0.0f, 1.0f)
    )

    Box(modifier = Modifier.fillMaxWidth().height(400.dp), contentAlignment = Alignment.Center) {
        if (isLoading) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(400.dp),
                contentScale = ContentScale.FillHeight,
                iterations = LottieConstants.IterateForever,
                clipSpec = Progress(0.1f, 0.6f)
            )
        } else {
            LottieAnimation(
                progress = progress,
                composition = composition,
                modifier = Modifier.size(400.dp),
                contentScale = ContentScale.FillHeight
            )
            if (progress == 1.0f) {
                finishListener.invoke()
            }
        }
    }
}