package com.vbshuliar.apple_of_fortune.gameplay.domain.model

data class Cell(
    val isHealthy: Boolean = true,
    val isActive: Boolean = false,
    val isVisible: Boolean = false
)
