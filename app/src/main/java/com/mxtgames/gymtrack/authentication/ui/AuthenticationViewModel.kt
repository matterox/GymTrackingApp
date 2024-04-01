package com.mxtgames.gymtrack.authentication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(): ViewModel() {
    private val _authUiState: MutableStateFlow<AuthScreenState> = MutableStateFlow(AuthScreenState.Success)
    val authUiState: StateFlow<AuthScreenState> = _authUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthScreenState.Loading,
    )
    fun onAction(action: AuthScreenActions) {
        when(action) {
            is AuthScreenActions.OnLoginPressed -> authenticate(action.email, action.password)
        }
    }

    private fun authenticate(email: String, password: String) {
        // TODO: Move to UseCase
        viewModelScope.launch {
            _authUiState.emit(AuthScreenState.Loading)
            // TODO: Do login logic
            delay(1000)
            _authUiState.emit(AuthScreenState.Error("Such an error"))
        }
    }
}

