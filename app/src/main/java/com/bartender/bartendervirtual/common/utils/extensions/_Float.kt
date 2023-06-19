package com.bartender.bartendervirtual.common.utils.extensions

fun Float?.orZero(): Float {
    return this ?: 0F
}

fun Float.toIntegerOrFloat(): Number {
    return if (this % 1 == 0.0f) {
        this.toInt()
    } else {
        this
    }
}