package com.example.randomnumber.di

import com.example.randomnumber.data.constants.Constants
import com.example.randomnumber.data.service.remote.NumberApi
import com.example.randomnumber.data.service.remote.NumberRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): NumberApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumberApi::class.java)

    @Singleton
    @Provides
    fun provideNumberRemoteDataSource(numberApi: NumberApi): NumberRemoteDataSource =
        NumberRemoteDataSource(numberApi)
}
