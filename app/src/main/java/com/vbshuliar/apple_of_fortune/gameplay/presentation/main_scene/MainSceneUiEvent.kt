package com.vbshuliar.apple_of_fortune.gameplay.presentation.main_scene

sealed class MainSceneUiEvent {
    data class Winner(val row: Int) : MainSceneUiEvent()
    data object Loser : MainSceneUiEvent()
}
