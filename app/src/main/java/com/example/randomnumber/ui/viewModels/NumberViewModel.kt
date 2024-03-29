package com.example.randomnumber.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomnumber.data.constants.Constants
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
class NumberViewModel @Inject constructor(
    private val numberInfoImpl: NumberInfoRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    private val errorFlow = numberInfoImpl.errorFlow.onEach {
        _uiState.update { it.copy(errorMessage = Constants.ERROR_MESSAGE) }
    }
    init {
        getNumberInfo()
        errorFlow.launchIn(viewModelScope)
    }
    private fun getNumberInfo() {
        numberInfoImpl.getNumber()
            .onEach { dataNumber ->
                if (dataNumber.isNotEmpty()) {
                    _uiState.update { it.copy(entity = dataNumber, number = (dataNumber.size - 1).toString()) }
                }
            }.launchIn(viewModelScope)
    }

     fun fetchNumber(number: String) {
        viewModelScope.launch {
            numberInfoImpl.fetchNumber(number).launchIn(viewModelScope)
        }
    }

    fun fetchRandomNumber() {
        viewModelScope.launch {
            numberInfoImpl.fetchRandomNumber().launchIn(viewModelScope)
        }
    }
}
