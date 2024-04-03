package com.mxtgames.gymtrack.domain.repository.userdata

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mxtgames.gymtrack.domain.data.UserAuthData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val USER_DATA_STORE_NAME = "used_data_store"

class UserDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): UserDataRepository {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            USER_DATA_STORE_NAME
        )
        val USER_DISPLAY_NAME_KEY = stringPreferencesKey("USER_DISPLAY_NAME")
        val USER_EMAIL_KEY = stringPreferencesKey("USER_EMAIL")
        val USER_EMAIL_VERIFIED_KEY = booleanPreferencesKey("USER_EMAIL_VERIFIED")
    }

    override suspend fun save(data: UserAuthData) {
        context.dataStore.edit { preference ->
            data.apply {
                preference[USER_DISPLAY_NAME_KEY] = displayName
                preference[USER_EMAIL_KEY] = email
                preference[USER_EMAIL_VERIFIED_KEY] = emailVerified
            }
        }
    }

    private val userAuthData = context.dataStore.data.map { preferences ->
        UserAuthData(
            displayName = preferences[USER_DISPLAY_NAME_KEY] ?: "",
            email = preferences[USER_EMAIL_KEY] ?: "",
            emailVerified = preferences[USER_EMAIL_VERIFIED_KEY] ?: false
        )
    }

    override suspend fun load(): UserAuthData {
        return userAuthData.first()
    }

    override suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }
}