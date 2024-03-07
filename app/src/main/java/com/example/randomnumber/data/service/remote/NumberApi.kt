package com.example.randomnumber.data.service.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApi {
    @GET("{number}")
    suspend fun getInfoNumber( @Path("number") number: String): Response<String>

    @GET("/random/math")
    suspend fun getRandomNumber(): Response<String>
}