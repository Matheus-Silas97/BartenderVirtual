package com.example.letsdrink.common.utils.extensions

fun Int?.orZero(): Int {
    return this ?: 0
}