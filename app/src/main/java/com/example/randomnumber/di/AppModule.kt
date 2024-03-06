package com.example.randomnumber.di

import com.example.randomnumber.data.impl.NumberInfoImpl
import com.example.randomnumber.data.service.remote.NumberApi
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
    fun provideNumberInfoImpl(numberRemoteDataSource: NumberRemoteDataSource) : NumberInfoImpl =
        NumberInfoImpl(numberRemoteDataSource)

    @Singleton
    @Provides
    fun provideNumberRemoteDataSource(numberApi: NumberApi) : NumberRemoteDataSource =
        NumberRemoteDataSource(numberApi)

}
