package com.mxtgames.gymtrack.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.mxtgames.gymtrack.domain.repository.auth.FirebaseAuthRepository
import com.mxtgames.gymtrack.domain.repository.auth.FirebaseAuthRepositoryImpl
import com.mxtgames.gymtrack.domain.repository.userdata.UserDataRepository
import com.mxtgames.gymtrack.domain.repository.userdata.UserDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseAuthRepository(
        firebaseAuth: FirebaseAuth,
        coroutineDispatcher: CoroutineDispatcher,
    ): FirebaseAuthRepository {
        return FirebaseAuthRepositoryImpl(
            firebaseAuth,
            coroutineDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideUserDataRepository(
        @ApplicationContext context: Context,
    ): UserDataRepository {
        return UserDataRepositoryImpl(
            context,
        )
    }
}