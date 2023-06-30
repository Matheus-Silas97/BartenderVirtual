package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerHorizontal(value: Int = 8){
    Spacer(modifier = Modifier.width(value.dp))
}

@Composable
fun SpacerVertical(value: Int = 8){
    Spacer(modifier = Modifier.height(value.dp))
}