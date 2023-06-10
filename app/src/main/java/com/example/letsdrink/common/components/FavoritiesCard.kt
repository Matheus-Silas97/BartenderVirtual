package com.example.letsdrink.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.letsdrink.R
import com.example.letsdrink.domain.model.DrinkFavorite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteCard(model: DrinkFavorite, onClick: () -> Unit, remove: (id: DrinkFavorite) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(all = 8.dp)

        ) {
            ImageUrl(
                url = model.image,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .align(Alignment.CenterVertically)
            )

            TextNormal(
                text = model.name, textAlign = TextAlign.Center, modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                maxLines = 1
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_select),
                contentDescription = "favorite icon",
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { remove(model) }
            )
        }
    }
}