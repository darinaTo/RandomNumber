package com.example.randomnumber.util

import com.example.randomnumber.domain.entities.NumberUIEntity
import com.example.randomnumber.domain.entities.dbEntities.NumberDbEntity
import com.example.randomnumber.domain.entities.network.NumberApiEntity

fun NumberApiEntity.toNumberDBEntity() : NumberDbEntity {
    return NumberDbEntity(
        number = this.number,
        info = this.info
    )
}

fun  String.toApiEntity() : NumberApiEntity {
    return NumberApiEntity(
        number = this.substringBefore(" "),
        info = this.substringAfter(" ").trim()
    )
}


fun NumberDbEntity.toUiEntity() : NumberUIEntity {
    return NumberUIEntity(
        number = this.number,
        info = this.info,
        id = this.id
    )
}