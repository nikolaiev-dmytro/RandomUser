package com.android.randomuser.data.source

import com.android.randomuser.data.User
import kotlinx.coroutines.flow.Flow

interface UsersDataSource {
    suspend fun fetchUsers(userCount: Int): Result<List<User>>
    fun observeHistory(): Flow<List<User>>
    suspend fun insertUsers(users: List<User>)
}