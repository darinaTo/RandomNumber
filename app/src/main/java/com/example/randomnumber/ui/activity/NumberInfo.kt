package com.example.randomnumber.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomnumber.ui.viewModels.NumberInfoViewModel

@Composable
fun NumberInfoScreen(
    viewModel: NumberInfoViewModel = hiltViewModel()
) {
    viewModel.getNumberInfo(1)
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Text(text = uiState.value.info,
            color = Color.Black)
    }
}