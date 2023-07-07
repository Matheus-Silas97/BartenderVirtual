package com.bartender.bartendervirtual.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.unit.dp

@Composable
fun CardGeneric(height: Int = 48, content: @Composable () -> Unit) {
    Column() {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(height.dp)
                .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            elevation = 6.dp
        ) {
            content()
        }
        SpacerVertical()
    }
}