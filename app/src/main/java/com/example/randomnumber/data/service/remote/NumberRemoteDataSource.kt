package com.example.randomnumber.data.service.remote

import java.io.IOException
import javax.inject.Inject

class NumberRemoteDataSource @Inject constructor(private val numberApi: NumberApi) {

    suspend fun fetchNumberInfo(number: String): Result<String> {
        val numberInfo = numberApi.getInfoNumber(number)
            return (numberInfo.body()?.let { Result.success(it) }
                ?: Result.failure(IOException("No data received.")))
    }

    suspend fun fetchRandomNumber() : Result<String> {
        val randomNumber = numberApi.getRandomNumber()
        return (randomNumber.body()?.let { Result.success(it) }
            ?: Result.failure(IOException("No data received.")))
    }
}
