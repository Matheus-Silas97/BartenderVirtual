package com.bartender.bartendervirtual.common.utils.extensions

fun Float?.orZero(): Float {
    return this ?: 0F
}