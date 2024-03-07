package com.example.randomnumber.data.service.local

import com.example.randomnumber.domain.entities.dbEntities.NumberDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NumberLocalDataSource @Inject constructor(
    private val numberDao: NumberDao
) {
    suspend fun insertNumber(numberEntity: NumberDbEntity) {
        withContext(Dispatchers.IO) {
            numberDao.insertNumber(numberEntity)
        }
    }

    fun getNumber() = numberDao.getAllNumber()
}