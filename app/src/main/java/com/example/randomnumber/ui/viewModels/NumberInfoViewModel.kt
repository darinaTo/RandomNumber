package com.example.randomnumber.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomnumber.data.impl.NumberInfoImpl
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
class NumberInfoViewModel  @Inject constructor(
    private val numberInfoImpl: NumberInfoImpl
) : ViewModel(){
    private val _uiState =  MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()



       fun getNumberInfo(number : Int) {
           viewModelScope.launch {
               numberInfoImpl.fetchNewRandomQuote(number).onEach { data ->
                   _uiState.update { it.copy(number = data.number, info = data.info) }
               }.launchIn(viewModelScope)
           }
     }
    fun updateText(number: String) {
        _uiState.update { it.copy(number = number) }
    }
}