package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vbshuliar.apple_of_fortune.gameplay.domain.model.CellState
import com.vbshuliar.apple_of_fortune.gameplay.domain.model.getMultiplierForRow
import com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene.MainSceneEvent

@Composable
fun RowOfCellCards(
    rowIndex: Int,
    cellStates: List<CellState>,
    onEvent: (MainSceneEvent) -> Unit
) {
    Row {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .width(80.dp)
                .align(CenterVertically)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    text = "${getMultiplierForRow(rowIndex)}",
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.Center)
                )
            }
        }
        cellStates.forEach { cellState ->
            CellCard(cellState = cellState, onEvent = onEvent)
        }
    }
}
