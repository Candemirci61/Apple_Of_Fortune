package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

sealed class MainSceneEvent {
    data class OnCellPress(val row: Int, val column: Int) : MainSceneEvent()
    data object OnCollectPress : MainSceneEvent()
}
