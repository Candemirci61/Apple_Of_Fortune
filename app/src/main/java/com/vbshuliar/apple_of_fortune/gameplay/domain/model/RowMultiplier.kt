package com.vbshuliar.apple_of_fortune.gameplay.domain.model

data class RowMultiplier(val row: Int, val multiplier: Double)

val rowMultipliers = listOf(
    RowMultiplier(row = 0, multiplier = 1.23),
    RowMultiplier(row = 1, multiplier = 1.54),
    RowMultiplier(row = 2, multiplier = 1.93),
    RowMultiplier(row = 3, multiplier = 2.41),
    RowMultiplier(row = 4, multiplier = 4.02),
    RowMultiplier(row = 5, multiplier = 6.71),
    RowMultiplier(row = 6, multiplier = 11.18),
    RowMultiplier(row = 7, multiplier = 27.97),
    RowMultiplier(row = 8, multiplier = 69.93),
    RowMultiplier(row = 9, multiplier = 349.68),
)

fun getMultiplierForRow(rowNumber: Int): Double {
    val multiplierEntry = rowMultipliers.find { it.row == rowNumber }
    return multiplierEntry?.multiplier ?: 1.0
}
