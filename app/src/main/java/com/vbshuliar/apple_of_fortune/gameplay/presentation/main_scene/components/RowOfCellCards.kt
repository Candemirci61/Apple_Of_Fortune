package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.vbshuliar.apple_of_fortune.gameplay.domain.model.CellState
import com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene.MainSceneEvent

@Composable
fun RowOfCellCards(
    cellStates: List<CellState>,
    onEvent: (MainSceneEvent) -> Unit
) {
    Row {
        cellStates.forEach { cellState ->
            CellCard(cellState = cellState, onEvent = onEvent)
        }
    }
}
