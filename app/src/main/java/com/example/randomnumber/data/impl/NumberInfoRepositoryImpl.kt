package com.example.randomnumber.data.impl

import com.example.randomnumber.data.service.local.NumberLocalDataSource
import com.example.randomnumber.data.service.remote.NumberRemoteDataSource
import com.example.randomnumber.domain.entities.NumberUIEntity
import com.example.randomnumber.domain.repo.NumberInfoRepository
import com.example.randomnumber.util.toApiEntity
import com.example.randomnumber.util.toNumberDBEntity
import com.example.randomnumber.util.toUiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NumberInfoRepositoryImpl @Inject constructor(
    private val numberRemoteDataSource: NumberRemoteDataSource,
    private val numberLocalDataSource: NumberLocalDataSource
) : NumberInfoRepository {

    private val _errorFlow = MutableSharedFlow<Error>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val errorFlow = _errorFlow.asSharedFlow()
    override fun getNumber(): Flow<List<NumberUIEntity>> =
        numberLocalDataSource.getNumber().map { data ->
            data.map { it.toUiEntity() }
        }

    override suspend fun fetchNumber(number: String): Flow<NumberUIEntity> =
        flow {
            runCatching {
                numberRemoteDataSource.fetchNumberInfo(number)
            }.onSuccess { data ->
                val apiEntity = data.getOrThrow().toApiEntity()
                val dbEntity = apiEntity.toNumberDBEntity()
                numberLocalDataSource.insertNumber(dbEntity)
                emit(dbEntity.toUiEntity())
            }.onFailure { exception ->
                _errorFlow.tryEmit(Error(exception))
            }

        }.flowOn(Dispatchers.IO)

    override suspend fun fetchRandomNumber(): Flow<String> = flow {
        runCatching {
            numberRemoteDataSource.fetchRandomNumber()
        }.onSuccess { data ->
            val entity = data.getOrThrow().toApiEntity()
            numberLocalDataSource.insertNumber(entity.toNumberDBEntity())
            emit(data.getOrThrow())
        }.onFailure { exception ->
            _errorFlow.tryEmit(java.lang.Error(exception))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getInfoById(id: Int): Flow<String> {
        return withContext(Dispatchers.IO) {
            numberLocalDataSource.getInfoById(id)
        }
    }

}
