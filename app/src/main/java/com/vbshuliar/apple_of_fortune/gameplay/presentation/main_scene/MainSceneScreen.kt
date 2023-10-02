package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vbshuliar.apple_of_fortune.gameplay.domain.model.Cell
import com.vbshuliar.apple_of_fortune.gameplay.utils.LOSER_MESSAGE
import com.vbshuliar.apple_of_fortune.gameplay.utils.WINNER_MESSAGE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSceneScreen(viewModel: MainSceneViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is MainSceneUiEvent.Loser -> {
                    snackbarHostState.showSnackbar(message = LOSER_MESSAGE)
                }

                is MainSceneUiEvent.Winner -> {
                    snackbarHostState.showSnackbar(message = WINNER_MESSAGE)
                }
            }

        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Apple Of Fortune") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.table.reversed()) { rowOfCells ->
                    RowOfApples(cells = rowOfCells)
                }
            }
        }

    }
}

@Composable
fun RowOfApples(cells: List<Cell>) {
    Row {
        cells.forEach { cell ->
            AppleCell(isActive = cell.isActive, isHealthy = cell.isHealthy)
        }
    }
}

@Composable
fun AppleCell(isActive: Boolean, isHealthy: Boolean) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(50.dp)
    ) {
        // You can customize the content inside the Card to represent an apple
        Text(
            text = if (isHealthy) "🍏" else "🪱",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

