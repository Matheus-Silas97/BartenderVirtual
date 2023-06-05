package com.example.letsdrink.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letsdrink.common.commons_custom.ImageUrl
import com.example.letsdrink.common.commons_custom.TextNormal
import com.example.letsdrink.domain.model.Drinks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkCard(model: Drinks, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 0.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
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
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    DrinkCard(model = Drinks(1, "teste", ""), {})
}