package com.mxtgames.gymtrack.domain.repository.auth

import com.mxtgames.gymtrack.domain.data.UserAuthData
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    suspend fun loginWithCredentials(email: String, password: String): Flow<UserAuthData>
}