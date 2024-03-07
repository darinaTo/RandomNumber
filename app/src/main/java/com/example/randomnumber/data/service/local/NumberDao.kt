package com.example.randomnumber.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomnumber.domain.entities.dbEntities.NumberDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {
    @Query("SELECT * FROM number")
    fun getAllNumber(): Flow<List<NumberDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNumber(numberEntity: NumberDbEntity)
}
