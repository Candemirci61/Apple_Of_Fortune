package com.vbshuliar.apple_of_fortune.gameplay.domain.use_cases

import com.vbshuliar.apple_of_fortune.gameplay.domain.model.Cell

class GenerateTable {
    operator fun invoke(): List<List<Cell>> {
        val table = mutableListOf<List<Cell>>()
        for (i in 0..9) {
            val countRottenApples = when (i) {
                in 0..3 -> 1
                in 4..6 -> 2
                in 7..8 -> 3
                9 -> 4
                else -> 0
            }

            val shuffledRow = generateRow(countRottenApples)
            table.add(shuffledRow)
        }
        return table
    }

    private fun generateRow(countUnhealthyCells: Int): List<Cell> {
        val shuffledCells = mutableListOf<Cell>()
        for (i in 1..5) {
            if (i <= countUnhealthyCells) {
                shuffledCells.add(Cell(isHealthy = false))
            } else {
                shuffledCells.add(Cell())

            }
        }
        shuffledCells.shuffle()
        return shuffledCells
    }
}
