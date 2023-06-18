package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bartender.bartendervirtual.domain.model.Category

@Composable
fun CategoryCard(
    category: Category,
    selectCategory: (categoryId: Long, categoryName: String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clickable {
                selectCategory(category.id, category.name)
            }
            .fillMaxSize()
            .aspectRatio(1.0F)
            .padding(8.dp)
            .shadow(elevation = 12.dp),
        elevation = 4.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            TextSubTitle(
                text = category.name,
                maxLines = 2,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(6.dp)
                    .zIndex(1f)
            )
        }
    }
}