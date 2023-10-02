package com.vbshuliar.apple_of_fortune.gameplay.domain.model

data class CellState(
    val isHealthy: Boolean = true,
    var isActive: Boolean = false,
    var isVisible: Boolean = false,
    var rowIndex: Int = 0,
    var columnIndex: Int = 0,
) {
    val character: String
        get() {
            return when {
                isActive && !isVisible -> "❔"
                isVisible && isHealthy -> "🍏"
                isVisible && !isHealthy -> "🪱"
                else -> " "
            }
        }
}
