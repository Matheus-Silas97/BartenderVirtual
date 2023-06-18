package com.bartender.bartendervirtual.common.utils.extensions

fun Long?.orZero(): Long {
    return this ?: 0L
}