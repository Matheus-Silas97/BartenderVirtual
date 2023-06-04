package com.example.letsdrink.common.utils

fun Long?.orZero(): Long {
    return this ?: 0L
}