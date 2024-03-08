package com.example.randomnumber.ui.activity

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomnumber.ui.viewModels.NumberInfoViewModel

@Composable
fun InfoScreen(viewModel: NumberInfoViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Text(text = uiState.currentInfo)

    
}