package com.example.randomnumber.domain.repo

import com.example.randomnumber.domain.entities.NumberUIEntity
import kotlinx.coroutines.flow.Flow

interface NumberInfoRepository {

    fun getRandomQuotes(): Flow<Int>

     suspend fun fetchNewRandomQuote(number : Int): Flow<NumberUIEntity>
}