package com.example.randomnumber.data.service.local

import com.example.randomnumber.domain.entities.dbEntities.NumberDbEntity
import javax.inject.Inject

class NumberLocalDataSource @Inject constructor(
    private val numberDao: NumberDao
) {
     fun insertNumber(numberEntity: NumberDbEntity) {
            numberDao.insertNumber(numberEntity)

    }

    fun getNumber() = numberDao.getNumber()
}