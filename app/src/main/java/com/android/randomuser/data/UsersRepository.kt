package com.android.randomuser.data

import androidx.lifecycle.LiveData
import com.android.randomuser.network.models.User

interface UsersRepository {
    fun fetchUsers(usersCount: Int)
    fun observeUsers(): LiveData<List<User>>
    fun getUser(userId: String): Result<User>
}