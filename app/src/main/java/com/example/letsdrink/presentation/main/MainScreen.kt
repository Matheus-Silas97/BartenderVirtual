package com.example.letsdrink.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.letsdrink.core.components.BottomBarComponent
import com.example.letsdrink.core.navigation.NavigationNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBarComponent(navController = navController) }
    ) {
        NavigationNavGraph(navController = navController)
    }
}