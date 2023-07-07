package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bartender.bartendervirtual.R.drawable
import com.bartender.bartendervirtual.common.utils.extensions.toIntegerOrFloat
import com.bartender.bartendervirtual.domain.model.Category
import com.bartender.bartendervirtual.domain.model.DrinkFavorite
import com.bartender.bartendervirtual.domain.model.Drinks
import com.bartender.bartendervirtual.domain.model.IngredientsListDrinkDetails

@Composable
fun CardGeneric(height: Int = 48, content: @Composable () -> Unit) {
    Column() {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(height.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(12.dp)
                )
                .fillMaxWidth(),
            elevation = 6.dp
        ) {
            content()
        }
        SpacerVertical()
    }
}

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

@Composable
fun CategoryCard(
    category: Category,
    selectCategory: (categoryId: Long, categoryName: String) -> Unit
) {
    CardGeneric() {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { selectCategory(category.id, category.name) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextNormal(
                    text = category.name, textAlign = TextAlign.Center, modifier = Modifier
                        .padding(start = 8.dp),
                    maxLines = 2
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "arrow icon",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun FavoriteCard(model: DrinkFavorite, onClick: () -> Unit, remove: (id: DrinkFavorite) -> Unit) {
    CardGeneric(height = 100) {
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
                        .size(size = 90.dp)
                        .align(Alignment.CenterVertically)
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
                    painter = painterResource(id = drawable.ic_favorite_select),
                    contentDescription = "favorite icon",
                    tint = Color.Red,
                    modifier = Modifier
                        .clickable { remove(model) }
                )
            }

        }
    }
}

@Composable
fun IngredientsCard(model: IngredientsListDrinkDetails, selectIngredient: (drinkId: Long) -> Unit) {
    CardGeneric {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { selectIngredient(model.ingredient.id) }
                .fillMaxSize()
                .padding(all = 4.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageUrl(
                    url = model.ingredient.image,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Inside
                )

                Column {
                    TextNormal(
                        text = model.ingredient.name,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 8.dp),
                        maxLines = 1
                    )
                    TextNormalSmall(
                        text = "${model.amount.toIntegerOrFloat()} ${model.measure.name}",
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 8.dp),
                        maxLines = 1
                    )

                }
            }


            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "arrow icon",
                    tint = Color.Black
                )
            }
        }
    }
}