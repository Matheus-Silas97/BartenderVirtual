package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bartender.bartendervirtual.R

@Composable
fun EmptyListComponent(msg: String = "Lista vazia", modifier: Modifier = Modifier.fillMaxSize()) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cocktail),
                contentDescription = "cocktail image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(50.dp)
            )

            TextTitle(
                text = msg,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
    }

}