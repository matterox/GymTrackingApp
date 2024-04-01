package com.mxtgames.gymtrack.authentication.ui

sealed class AuthScreenState {
    data object Loading: AuthScreenState()
    data class Error(val message: String): AuthScreenState()
    data object Success: AuthScreenState()
    data object AuthSuccess: AuthScreenState()
}