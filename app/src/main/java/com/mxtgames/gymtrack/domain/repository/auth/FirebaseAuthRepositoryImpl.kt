package com.mxtgames.gymtrack.domain.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.mxtgames.gymtrack.domain.data.UserAuthData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher,
): FirebaseAuthRepository {
    override suspend fun loginWithCredentials(email: String, password: String) = flow {
        val user = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(
            UserAuthData(
                displayName = user.user?.displayName ?: "",
                email = user.user?.email ?: "",
                emailVerified = user.user?.isEmailVerified ?: false
            )
        )
    }.flowOn(ioDispatcher)
}