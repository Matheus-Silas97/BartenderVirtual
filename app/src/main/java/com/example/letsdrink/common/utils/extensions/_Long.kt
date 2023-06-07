package com.example.letsdrink.common.utils.extensions

fun Long?.orZero(): Long {
    return this ?: 0L
}