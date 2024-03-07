package com.example.randomnumber.util

import com.example.randomnumber.domain.entities.NumberUIEntity
import com.example.randomnumber.domain.entities.dbEntities.NumberDbEntity
import com.example.randomnumber.domain.entities.network.NumberApiEntity

fun NumberApiEntity.toNumberEntity() : NumberDbEntity {
    return NumberDbEntity(
        number = this.number,
        info = this.info
    )
}

fun  String.toApiEntity(number : String) : NumberApiEntity {
    return NumberApiEntity(
        number = number,
        info = this
    )
}

fun NumberApiEntity.toUiEntity() : NumberUIEntity {
    return NumberUIEntity(
        number = this.number,
        info = this.info
    )
}