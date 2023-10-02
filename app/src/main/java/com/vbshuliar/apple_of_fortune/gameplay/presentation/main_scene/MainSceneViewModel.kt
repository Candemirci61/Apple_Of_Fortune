package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vbshuliar.apple_of_fortune.gameplay.domain.use_cases.GenerateTable
import com.vbshuliar.apple_of_fortune.gameplay.utils.MAX_ROW
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

                println("Row: ${event.row} Column: ${event.column}")
                val currentTable = state.value.table
                val currentRow = state.value.currentRow

                if (currentRow == event.row) {
                    revealCell(event.row, event.column)
                    if (currentTable[event.row][event.column].isHealthy) {
                        if (currentRow == MAX_ROW) {
                            sendUiEvent(MainSceneUiEvent.Winner(state.value.currentRow))
                        } else {
                            moveUp()
                        }
                    } else {
                        sendUiEvent(MainSceneUiEvent.Loser)
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

    private fun moveUp() {
        _state.value = state.value.copy(currentRow = state.value.currentRow + 1)
        val currentState = state.value
        val currentRow = currentState.currentRow
        val currentTable = currentState.table
        val updatedTable = currentTable.mapIndexed { rowIndex, cellRow ->
            cellRow.map { cell ->
                cell.copy(isActive = rowIndex == currentRow)
            }
        }
        _state.value = currentState.copy(table = updatedTable)
    }

    private fun revealCell(row: Int, column: Int) {
        val currentState = state.value
        val currentTable = currentState.table
        val updatedTable = currentTable.mapIndexed { rowIndex, cellRow ->
            cellRow.mapIndexed { columnIndex, cell ->
                cell.copy(isVisible = column == columnIndex && row == rowIndex)
            }
        }
        _state.value = currentState.copy(table = updatedTable)
    }

    private fun revealTable() {
        val currentState = state.value
        val currentTable = currentState.table
        val updatedTable = currentTable.map { cellRow ->
            cellRow.map { cell ->
                cell.copy(isVisible = true)
            }
        }
        _state.value = currentState.copy(table = updatedTable)
    }
}
