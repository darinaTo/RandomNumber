package com.example.randomnumber.domain.repo

import com.example.randomnumber.domain.entities.NumberUIEntity
import kotlinx.coroutines.flow.Flow

interface NumberInfoRepository {

    fun getRandomQuotes(): Flow<List<NumberUIEntity>>

     suspend fun fetchNewRandomQuote(number : String): Flow<NumberUIEntity>
}