package com.example.letsdrink.common.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.letsdrink.R

@Composable
fun ImageUrl(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Inside
) {
    val imagePainter = painterResource(id = R.drawable.ic_not_found)

    if (url.isNotEmpty()) {
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .build(),
        )
    }

    Image(
        painter = imagePainter,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
    )
}