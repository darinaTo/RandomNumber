package com.example.randomnumber.util

import com.example.randomnumber.domain.entities.NumberUIEntity

data class UiState(
    val entity : List<NumberUIEntity> = emptyList(),
    val number : String = ""
)
