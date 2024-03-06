package com.example.randomnumber.data.impl

import com.example.randomnumber.data.service.remote.NumberRemoteDataSource
import com.example.randomnumber.domain.repo.NumberInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NumberInfoImpl @Inject constructor(
    private val numberRemoteDataSource: NumberRemoteDataSource
) : NumberInfoRepository {
    override fun getRandomQuotes(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun fetchNewRandomQuote(number : Int): Flow<Result<String>> = flow {
       try {
           val number = numberRemoteDataSource.fetchNumberInfo(number)
           emit(number)
       } catch (exception : Exception) {
           emit(Result.failure(exception))
       }
    }.flowOn(Dispatchers.IO)
}