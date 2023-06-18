package com.bartender.bartendervirtual.common.utils.extensions

fun Int?.orZero(): Int {
    return this ?: 0
}