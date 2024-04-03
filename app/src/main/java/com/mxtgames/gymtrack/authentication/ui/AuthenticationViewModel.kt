package com.mxtgames.gymtrack.authentication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxtgames.gymtrack.domain.usecase.LoginResult
import com.mxtgames.gymtrack.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
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
            loginUseCase.invoke(
                email, password
            ).onStart {
                _authUiState.emit(AuthScreenState.Loading)
            }.onEach {
                when (it) {
                    is LoginResult.Error -> _authUiState.emit(AuthScreenState.Error(it.error.message ?: ""))
                    LoginResult.Success -> _authUiState.emit(AuthScreenState.Success)
                }
            }
        }
    }
}

