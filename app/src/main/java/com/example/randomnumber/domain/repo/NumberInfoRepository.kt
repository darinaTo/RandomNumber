package com.example.randomnumber.domain.repo

import kotlinx.coroutines.flow.Flow

interface NumberInfoRepository {

    fun getRandomQuotes(): Flow<Int>

    fun fetchNewRandomQuote(number : Int): Flow<Result<String>>
}