package com.mxtgames.gymtrack.domain.repository.userdata

import com.mxtgames.gymtrack.domain.data.UserAuthData

interface UserDataRepository {
    suspend fun save(data: UserAuthData)
    suspend fun load(): UserAuthData
    suspend fun clear()
}