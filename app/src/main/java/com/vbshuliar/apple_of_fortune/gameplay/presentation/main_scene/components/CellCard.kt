package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vbshuliar.apple_of_fortune.gameplay.domain.model.CellState
import com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene.MainSceneEvent

@Composable
fun CellCard(cellState: CellState, onEvent: (MainSceneEvent) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(50.dp)
            .clickable {
                onEvent(MainSceneEvent.OnCellClick(cellState.rowIndex, cellState.columnIndex))
            }
    ) {
        Text(
            text = if (cellState.isHealthy) "🍏" else "🪱",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

