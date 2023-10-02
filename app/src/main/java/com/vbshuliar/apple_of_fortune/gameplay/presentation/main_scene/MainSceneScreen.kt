package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene.components.RowOfCellCards
import com.vbshuliar.apple_of_fortune.gameplay.utils.LOSER_MESSAGE
import com.vbshuliar.apple_of_fortune.gameplay.utils.MAX_ROW_INDEX
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
                .fillMaxSize(),

            ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                itemsIndexed(state.table.reversed()) { rowIndex, rowOfCells ->
                    RowOfCellCards(
                        rowIndex = MAX_ROW_INDEX - rowIndex,
                        cellStates = rowOfCells,
                        onEvent = viewModel::onEvent
                    )
                }
            }
            Button(onClick = { MainSceneScreen() }) {

            }
        }

    }
}


