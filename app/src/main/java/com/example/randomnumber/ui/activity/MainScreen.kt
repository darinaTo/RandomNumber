package com.example.randomnumber.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomnumber.domain.entities.NumberUIEntity
import com.example.randomnumber.ui.viewModels.NumberInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInfoScreen(
    viewModel: NumberInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf("") }
    var showRandomNumber by remember { mutableStateOf(false) }
    var showNumberInfo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp), // Set the weight to 1f
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LightGray, unfocusedBorderColor = LightGray
                    )
                )

                //TODO: create method to pass value
                Button(
                    onClick = {
                        viewModel.fetchNumber(text)
                    }, modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                ) {
                    Text(text = "Get fact")
                }

                Button(
                    onClick = { viewModel.fetchRandomNumber() },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                ) {
                    Text(text = "Get random number")
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
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = entity.number,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.17f),
            textAlign = TextAlign.Center
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .weight(1f)
                .background(LightGray)
        ) {
            Text(
                text = entity.info,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 6.dp),
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
    }

}