package com.android.randomuser.data

import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun fetchUsers(usersCount: Int)
    fun observeUsers(): Flow<Result<List<User>>>
    fun observeHistory(): Flow<List<User>>
}