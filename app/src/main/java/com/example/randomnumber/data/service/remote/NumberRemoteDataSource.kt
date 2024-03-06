package com.example.randomnumber.data.service.remote

import javax.inject.Inject

class NumberRemoteDataSource @Inject constructor(private val numberApi: NumberApi) {

    suspend fun fetchNumberInfo(number: Int): Result<String> {
        val numberInfo = numberApi.getInfoNumber(number)
        return Result.success(numberInfo)/*.body()?.first()?.let { Result.success(it.toString()) }
            ?: Result.failure(IOException("Can't get data"))*/
    }
}
