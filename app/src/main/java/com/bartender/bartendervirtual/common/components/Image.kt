package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.bartender.bartendervirtual.R

@Composable
fun ImageUrl(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Inside
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        loading = { CircularProgressIndicator(modifier = modifier.padding(12.dp), strokeWidth = 1.dp) },
        error = { Image(painter = painterResource(R.drawable.ic_not_found), contentDescription = "error image") },
        contentDescription = "imagem",
        contentScale = contentScale,
        modifier = modifier
    )
}