package com.example.randomnumber.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomnumber.data.constants.Constants.ERROR_MESSAGE
import com.example.randomnumber.data.impl.NumberInfoRepositoryImpl
import com.example.randomnumber.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val numberInfoImpl: NumberInfoRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    private val errorFlow = numberInfoImpl.errorFlow.onEach {
        _uiState.update { it.copy(errorMessage = ERROR_MESSAGE) }
    }
    private val id: Int

    init {
        id = requireNotNull(savedStateHandle.get<Int>("id"))
        getInfoById()
        errorFlow.launchIn(viewModelScope)
    }

    private fun getInfoById() {
        viewModelScope.launch {
            numberInfoImpl.getInfoById(id).onEach { info ->
                _uiState.update { it.copy(currentInfo = info) }
            }.launchIn(viewModelScope)
        }
    }
}