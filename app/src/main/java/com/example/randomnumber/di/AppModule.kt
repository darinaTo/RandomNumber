package com.example.randomnumber.di

import com.example.randomnumber.data.impl.NumberInfoImpl
import com.example.randomnumber.data.service.local.NumberLocalDataSource
import com.example.randomnumber.data.service.remote.NumberRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNumberInfoImpl(
        numberRemoteDataSource: NumberRemoteDataSource,
        numberLocalDataSource: NumberLocalDataSource,
    ): NumberInfoImpl =
        NumberInfoImpl(numberRemoteDataSource, numberLocalDataSource)
}
