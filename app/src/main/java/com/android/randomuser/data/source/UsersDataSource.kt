package com.android.randomuser.data.source

import com.android.randomuser.network.models.User

interface UsersDataSource {
    suspend fun getUsers(usersCount: Int): com.android.randomuser.data.Result<List<User>>
}