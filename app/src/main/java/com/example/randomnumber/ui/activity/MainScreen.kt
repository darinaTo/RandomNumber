package com.example.randomnumber.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomnumber.domain.entities.NumberUIEntity
import com.example.randomnumber.ui.theme.Dimensions
import com.example.randomnumber.ui.viewModels.NumberInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInfoScreen(
    viewModel: NumberInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LightGray,
                        unfocusedBorderColor = LightGray
                    )
                )
                Spacer(Modifier.weight(1f))

                //TODO: create method to pass value
                Button(onClick = { viewModel.fetchNumber(text) }) {
                    Text(text = "Get fact")
                }
            }

            NumberInfo(list = uiState.entity)
        }
    }
}

@Composable
fun NumberInfo(
    list: List<NumberUIEntity>
) {
    val lazyListState = rememberLazyListState()
//TODO:state holding
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(13.dp),
        modifier = Modifier.padding(top = 14.dp)

    ) {
        items(list) { entity ->
            NumberCard(entity = entity)
        }
    }
}

@Composable
fun NumberCard(entity: NumberUIEntity) {
    Row (modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(text = entity.number)
       Spacer(modifier = Modifier.padding(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Dimensions.One),
        ) {
            Text(text = entity.info,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

        }
    }

}