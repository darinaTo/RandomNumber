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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomnumber.domain.entities.NumberUIEntity
import com.example.randomnumber.ui.viewModels.NumberViewModel
import com.example.randomnumber.util.isValidText

@Composable
fun NumberInfoScreen(
    onInfoTap: (Int) -> Unit,
    viewModel: NumberViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val message = uiState.errorMessage

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (message.isNotEmpty()) {
            ErrorDialog(
                message = message,
                Modifier.align(Alignment.Center)
            )
        } else {
            NumberTop(
                onInfoTap = onInfoTap,
                fetchData = { viewModel.fetchRandomNumber() },
                fetchNumber = { number -> viewModel.fetchNumber(number) },
                entity = uiState.entity
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberTop(
    onInfoTap: (Int) -> Unit,
    fetchData: () -> Unit,
    fetchNumber: (String) -> Unit,
    entity: List<NumberUIEntity>,
) {
    var text by remember { mutableStateOf("") }

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
                    .height(60.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightGray, unfocusedBorderColor = LightGray
                ),
                isError = !isValidText(text)
            )

            Button(
                onClick = {
                    fetchNumber(text)
                    text = ""
                }, modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(text = "Get fact")
            }

            Button(
                onClick = { fetchData()
                    text = ""},
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(text = "Get random number")
            }
        }

        NumberInfo(
            list = entity,
            onInfoTap = onInfoTap
        )

    }
}

@Composable
fun NumberInfo(
    list: List<NumberUIEntity>,
    onInfoTap: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
//TODO:state holding
    LazyColumn(
        reverseLayout = true,
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(13.dp),
        modifier = Modifier.padding(top = 14.dp)

    ) {
        items(list) { entity ->
            NumberCard(
                entity = entity,
                onInfoTap = onInfoTap
            )
        }
    }
}

@Composable
fun NumberCard(entity: NumberUIEntity, onInfoTap: (Int) -> Unit) {
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
        Button(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGray,
                contentColor = Color.White
            ),
            onClick = { onInfoTap(entity.id) }) {
            Text(
                text = entity.info,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
    }
}