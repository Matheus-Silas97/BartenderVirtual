package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bartender.bartendervirtual.R
import com.bartender.bartendervirtual.domain.model.DrinkFavorite

@Composable
fun FavoriteCard(model: DrinkFavorite, onClick: () -> Unit, remove: (id: DrinkFavorite) -> Unit) {
    CardGeneric {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(all = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageUrl(
                    url = model.image,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )

                TextNormal(
                    text = model.name, textAlign = TextAlign.Center, modifier = Modifier
                        .padding(start = 8.dp),
                    maxLines = 1
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite_select),
                    contentDescription = "favorite icon",
                    tint = Color.Red,
                    modifier = Modifier
                        .clickable { remove(model) }
                )
            }

        }
    }
}