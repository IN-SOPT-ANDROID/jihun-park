package org.sopt.sample.presentation.state

sealed class UiState {
    object Success : UiState()
    object Empty : UiState()
    object Failure : UiState()
    object Loading : UiState()
}