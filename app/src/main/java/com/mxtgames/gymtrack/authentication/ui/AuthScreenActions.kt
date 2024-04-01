package com.mxtgames.gymtrack.authentication.ui

sealed class AuthScreenActions {
    data class OnLoginPressed(val email: String, val password: String) : AuthScreenActions()
}