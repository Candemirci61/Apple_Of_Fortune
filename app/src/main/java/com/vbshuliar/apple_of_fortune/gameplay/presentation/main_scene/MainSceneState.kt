package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

import com.vbshuliar.apple_of_fortune.gameplay.domain.model.CellState

data class MainSceneState(
    val table: List<List<CellState>> = emptyList(),
    val currentRow: Int = 0,
)
