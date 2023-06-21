package com.bartender.bartendervirtual.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.bartender.bartendervirtual.common.components.BottomBarComponent
import com.bartender.bartendervirtual.common.navigation.BottomBarState.GONE
import com.bartender.bartendervirtual.common.navigation.BottomBarState.VISIBLE
import com.bartender.bartendervirtual.common.navigation.LocalBottomBarState
import com.bartender.bartendervirtual.common.navigation.NavigationNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalBottomBarState provides remember {
            mutableStateOf(GONE)
        }
    ) {
        Scaffold(
            bottomBar = {
                if (LocalBottomBarState.current.value == VISIBLE) {
                    BottomBarComponent(navController = navController)
                }
            }
        ) { innerPadding ->
            NavigationNavGraph(navController = navController, innerPadding = innerPadding)
        }
    }
}