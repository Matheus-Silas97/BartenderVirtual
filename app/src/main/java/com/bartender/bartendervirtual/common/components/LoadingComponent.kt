package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
    text: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
    ) {

        Row(horizontalArrangement = Arrangement.Start) {
            TextTitle(text = text, modifier = Modifier.padding(end = 50.dp, start = 12.dp))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            val composition by rememberLottieComposition(RawRes(raw.ic_loading))
            LottieAnimation(
                composition,
                modifier = Modifier.size(400.dp),
                contentScale = ContentScale.FillHeight,
                iterations = LottieConstants.IterateForever
            )
        }

    }
}

@Composable
fun LoadingLottieView(isLoading: Boolean) {
    val composition by rememberLottieComposition(RawRes(raw.ic_loading))
    val progress by animateLottieCompositionAsState(
        composition,
        clipSpec = Progress(0.2f, 0.35f),
        iterations = LottieConstants.IterateForever
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(400.dp),
                contentScale = ContentScale.FillHeight,
            )
        } else {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(400.dp),
                contentScale = ContentScale.FillHeight,
                iterations = LottieConstants.IterateForever
            )
        }
    }
}