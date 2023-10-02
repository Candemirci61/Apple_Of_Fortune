package com.vbshuliar.apple_of_fortune.gameplay.domain.use_cases

import com.vbshuliar.apple_of_fortune.gameplay.domain.model.CellState

class GenerateTable {
    operator fun invoke(): List<List<CellState>> {
        var table = mutableListOf<List<CellState>>()
        for (rowIndex in 0..9) {
            val countUnhealthyCells = when (rowIndex) {
                in 0..3 -> 1
                in 4..6 -> 2
                in 7..8 -> 3
                9 -> 4
                else -> 0
            }

            val shuffledRow = generateRow(countUnhealthyCells)
            table.add(shuffledRow)
        }
        enumerateCells(table)
        return table
    }

    private fun generateRow(countUnhealthyCells: Int): List<CellState> {
        val shuffledCellStates = mutableListOf<CellState>()
        for (columnIndex in 0..4) {
            if (columnIndex < countUnhealthyCells) {
                shuffledCellStates.add(CellState(isHealthy = false))
            } else {
                shuffledCellStates.add(CellState())
            }
        }
        shuffledCellStates.shuffle()
        return shuffledCellStates
    }

    private fun enumerateCells(table: List<List<CellState>>) {
        for (rowIndex in table.indices) {
            for (columnIndex in table[rowIndex].indices) {
                val cell = table[rowIndex][columnIndex]
                cell.rowIndex = rowIndex
                cell.columnIndex = columnIndex
            }
        }
    }
}
