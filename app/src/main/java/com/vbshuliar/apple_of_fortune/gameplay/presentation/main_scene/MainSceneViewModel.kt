package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vbshuliar.apple_of_fortune.gameplay.domain.use_cases.GenerateTable
import com.vbshuliar.apple_of_fortune.gameplay.utils.MAX_COLUMN_INDEX
import com.vbshuliar.apple_of_fortune.gameplay.utils.MAX_ROW_INDEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainSceneViewModel @Inject constructor(private val generateTable: GenerateTable) :
    ViewModel() {

    private val _state = mutableStateOf(MainSceneState(table = generateTable(), currentRow = 0))
    val state: State<MainSceneState> = _state

    private val _eventFlow = MutableSharedFlow<MainSceneUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: MainSceneEvent) {
        when (event) {
            is MainSceneEvent.OnCellClick -> {

                val currentRow = state.value.currentRow

                if (currentRow == event.row) {
                    revealCell(event.row, event.column)
                    val currentTable = state.value.table
                    if (currentTable[event.row][event.column].isHealthy) {
                        if (currentRow == MAX_ROW_INDEX) {
                            sendUiEvent(MainSceneUiEvent.Winner(state.value.currentRow))
                        } else {
                            moveUp(event)
                        }
                    } else {
                        sendUiEvent(MainSceneUiEvent.Loser)
                        revealTable()
                    }
                }
            }

            is MainSceneEvent.OnCollectClick -> {
                sendUiEvent(MainSceneUiEvent.Winner(state.value.currentRow))
            }
        }
    }

    private fun sendUiEvent(uiEvent: MainSceneUiEvent) {
        viewModelScope.launch {
            _eventFlow.emit(uiEvent)
        }
    }

    private fun moveUp(event: MainSceneEvent.OnCellClick) {
        val newRow = event.row + 1
        val updatedTable = _state.value.table.mapIndexed { rowIndex, cellRow ->
            val isCurrentRow = rowIndex == event.row
            val isNewRow = rowIndex == newRow

            cellRow.forEach { cell ->
                cell.isVisible =
                    (isCurrentRow && cell.columnIndex == event.column) || cell.isVisible
                cell.isActive = isNewRow
            }
            cellRow
        }
        _state.value = _state.value.copy(currentRow = newRow, table = updatedTable)
    }

    private fun revealCell(rowIndex: Int, columnIndex: Int) {
        val currentState = _state.value
        val currentTable = currentState.table

        val updatedTable = currentTable.map { cellRow ->
            cellRow.map { cellState ->
                val isVisible =
                    cellState.rowIndex == rowIndex && cellState.columnIndex == columnIndex || cellState.isVisible
                val isActive = false

                cellState.copy(isVisible = isVisible, isActive = isActive)
            }
        }

        _state.value = currentState.copy(table = updatedTable)
    }

    private fun revealTable() {
        for (rowIndex in 0..MAX_ROW_INDEX) {
            for (columnIndex in 0..MAX_COLUMN_INDEX) {
                revealCell(rowIndex, columnIndex)
            }
        }
    }

}
