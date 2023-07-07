package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bartender.bartendervirtual.domain.model.Drinks


@Composable
fun DrinkCard(model: Drinks, onClick: () -> Unit) {
    CardGeneric(height = 100) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            ImageUrl(
                url = model.image,
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.CenterVertically)
            )

            TextNormal(
                text = model.name, textAlign = TextAlign.Center, modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically),
                maxLines = 2
            )
        }

    }
}