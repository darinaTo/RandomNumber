package com.example.randomnumber.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomnumber.ui.theme.Dimensions
import com.example.randomnumber.ui.theme.Pink80
import com.example.randomnumber.ui.viewModels.NumberInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInfoScreen(
    viewModel: NumberInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Row(modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = uiState.number,
                    onValueChange = { newText ->
                        viewModel.updateText(newText)
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LightGray,
                        unfocusedBorderColor = LightGray
                    )
                )
                Spacer(Modifier.weight(1f))

                Button(onClick = { viewModel.getNumberInfo(uiState.number.toInt()) }) {
                    Text(text = "Get fact")
                }
            }
            Card(modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimensions.One),
                colors = CardDefaults.cardColors(Pink80)
            ) {
                Text(text = uiState.info)
            }
        }
    }
}

@Composable
fun NumberInfo(
    list: List<String>
) {
    val lazyListState = rememberLazyListState()
//TODO:state holding
   LazyColumn(
       state = lazyListState,
       modifier = Modifier.padding(12.dp)
   ) {
       items(list) { it ->
           Text(text = it)

       }
   }
}