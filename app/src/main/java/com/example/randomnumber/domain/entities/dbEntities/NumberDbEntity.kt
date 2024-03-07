package com.example.randomnumber.domain.entities.dbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number")
data class NumberDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "number") val number : String,
    @ColumnInfo(name = "info") val info : String
)
