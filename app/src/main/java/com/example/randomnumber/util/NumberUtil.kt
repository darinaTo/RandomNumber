package com.example.randomnumber.util

import androidx.core.text.isDigitsOnly

fun isValidText(text: String): Boolean {
    return text.isNotEmpty() && text.isDigitsOnly()
}