package com.example.letsdrink.common.utils.extensions

fun Float?.orZero(): Float {
    return this ?: 0F
}