package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

import com.vbshuliar.apple_of_fortune.gameplay.domain.model.Cell

data class MainSceneState(
    val table: List<List<Cell>> = emptyList(),
    val currentRow: Int = 0,
)
