package com.example.letsdrink.common.commons_custom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.letsdrink.common.theme.Typography

@Composable
fun TextTitle(text: String, modifier: Modifier = Modifier, textAlign: TextAlign? = null) {
    Text(
        text = text,
        modifier = modifier,
        style = Typography.titleLarge,
        color = Color.Black,
        fontWeight = FontWeight(700),
        textAlign = textAlign
    )
}

@Composable
fun TextSubTitle(text: String, modifier: Modifier = Modifier, textAlign: TextAlign? = null) {
    Text(
        text = text,
        modifier = modifier,
        style = Typography.titleMedium,
        textAlign = textAlign
    )
}

@Composable
fun TextNormal(text: String, modifier: Modifier = Modifier, textAlign: TextAlign? = null) {
    Text(
        text = text,
        modifier = modifier,
        style = Typography.bodyLarge,
        color = Color.Black,
        textAlign = textAlign
    )
}