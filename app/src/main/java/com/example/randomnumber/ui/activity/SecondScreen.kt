package com.example.randomnumber.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomnumber.ui.theme.Dimensions
import com.example.randomnumber.ui.theme.Purple80
import com.example.randomnumber.ui.viewModels.NumberInfoViewModel

@Composable
fun InfoScreen(viewModel: NumberInfoViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor =Purple80
            ),
            shape = RoundedCornerShape(Dimensions.One)
        ) {
            Text(
                text = uiState.currentInfo,
                fontSize = 25.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}