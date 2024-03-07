package com.example.randomnumber.data.impl

import com.example.randomnumber.data.service.local.NumberLocalDataSource
import com.example.randomnumber.data.service.remote.NumberRemoteDataSource
import com.example.randomnumber.domain.entities.NumberUIEntity
import com.example.randomnumber.domain.repo.NumberInfoRepository
import com.example.randomnumber.util.toApiEntity
import com.example.randomnumber.util.toNumberEntity
import com.example.randomnumber.util.toUiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NumberInfoImpl @Inject constructor(
    private val numberRemoteDataSource: NumberRemoteDataSource,
    private val numberLocalDataSource: NumberLocalDataSource
) : NumberInfoRepository {

    private val _errorFlow = MutableSharedFlow<Error>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val errorFlow = _errorFlow.asSharedFlow()
    override fun getRandomQuotes(): Flow<List<NumberUIEntity>> =
        numberLocalDataSource.getNumber().map { data ->
            data.map { it.toUiEntity() }
        }

    override suspend fun fetchNewRandomQuote(number: String): Flow<NumberUIEntity> =
        flow {
            runCatching {
                numberRemoteDataSource.fetchNumberInfo(number)
            }.onSuccess { data ->
                val entity = data.getOrThrow().toApiEntity(number)
                numberLocalDataSource.insertNumber(entity.toNumberEntity())
                emit(entity.toUiEntity())
            }.onFailure { exception ->
                _errorFlow.tryEmit(Error(exception))
            }

        }.flowOn(Dispatchers.IO)
}
