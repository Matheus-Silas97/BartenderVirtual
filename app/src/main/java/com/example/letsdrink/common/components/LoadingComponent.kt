package com.example.letsdrink.common.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.letsdrink.R.raw

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
@Preview
fun preview() {
    LoadingComponent("mensagem")
}