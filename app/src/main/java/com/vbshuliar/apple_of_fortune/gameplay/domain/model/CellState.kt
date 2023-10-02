package com.vbshuliar.apple_of_fortune.gameplay.domain.model

data class CellState(
    val isHealthy: Boolean = true,
    val isActive: Boolean = false,
    val isVisible: Boolean = false,
    var rowIndex: Int = 0,
    var columnIndex: Int = 0
)
