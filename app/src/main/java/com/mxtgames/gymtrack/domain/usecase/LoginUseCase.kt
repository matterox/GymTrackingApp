package com.mxtgames.gymtrack.domain.usecase

import com.mxtgames.gymtrack.domain.repository.auth.FirebaseAuthRepository
import com.mxtgames.gymtrack.domain.repository.userdata.UserDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val userDataRepository: UserDataRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        email: String, password: String
    ): Flow<LoginResult> = firebaseAuthRepository.loginWithCredentials(
        email,
        password
    ).map { userAuthData ->
        userDataRepository.save(userAuthData)
    }.map {
        LoginResult.Success
    }.catch {
        LoginResult.Error(it)
    }
}

sealed class LoginResult {
    data object Success: LoginResult()
    data class Error(val error: Throwable) : LoginResult()
}