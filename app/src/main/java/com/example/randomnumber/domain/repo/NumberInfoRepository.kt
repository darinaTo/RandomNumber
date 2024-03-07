package com.example.randomnumber.domain.repo

import com.example.randomnumber.domain.entities.NumberUIEntity
import kotlinx.coroutines.flow.Flow

interface NumberInfoRepository {

    fun getNumber(): Flow<List<NumberUIEntity>>

     suspend fun fetchNumber(number : String): Flow<NumberUIEntity>

     suspend fun fetchRandomNumber() : Flow<String>
}