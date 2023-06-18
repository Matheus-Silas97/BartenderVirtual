package com.bartender.bartendervirtual.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bartender.bartendervirtual.R

@Composable
fun ImageUrl(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Inside
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_not_found),
        placeholder = painterResource(R.drawable.ic_not_found),
        contentDescription = "imagem",
        contentScale = contentScale,
        modifier = modifier
    )
}