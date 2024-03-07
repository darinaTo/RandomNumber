package com.example.randomnumber.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomnumber.domain.entities.dbEntities.NumberDbEntity

@Database(entities = [NumberDbEntity::class],
    version = 1, exportSchema = false)
abstract class NumberDatabase : RoomDatabase() {
    abstract fun numberDao() : NumberDao
}