package com.example.randomnumber.di

import android.content.Context
import androidx.room.Room
import com.example.randomnumber.data.service.local.NumberDao
import com.example.randomnumber.data.service.local.NumberDatabase
import com.example.randomnumber.data.service.local.NumberLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext : Context) : NumberDatabase {
        return  Room.databaseBuilder(
            appContext,
            NumberDatabase::class.java,
            "number_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDao(appDatabase: NumberDatabase): NumberDao = appDatabase.numberDao()
    @Singleton
    @Provides
    fun provideNumberLocalDataSource(numberDao: NumberDao): NumberLocalDataSource =
        NumberLocalDataSource(numberDao)
}