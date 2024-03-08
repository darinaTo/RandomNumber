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
    fun getNumber(): Flow<List<NumberDbEntity>>

    @Query("SELECT info FROM NUMBER WHERE id =:id")
    fun getInfoById(id : Int) : Flow<String>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNumber(numberEntity: NumberDbEntity)
}
