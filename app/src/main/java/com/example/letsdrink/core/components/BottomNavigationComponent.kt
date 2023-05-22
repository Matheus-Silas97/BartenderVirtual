package com.example.letsdrink.core.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.letsdrink.core.navigation.BottomNavigationScreens

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavigationScreens.DrinksScreen,
        BottomNavigationScreens.FavoriteScreen,
        BottomNavigationScreens.Screen3,
    )
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        screens.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = { navController.navigate(item.route) }
            )
        }
    }

}
