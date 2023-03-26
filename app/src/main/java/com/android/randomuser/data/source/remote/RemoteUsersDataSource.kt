package com.android.randomuser.data.source.remote

import com.android.randomuser.data.Result
import com.android.randomuser.data.source.UsersDataSource
import com.android.randomuser.network.RandomUserService
import com.android.randomuser.network.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteUsersDataSource @Inject constructor(val usersService: RandomUserService) :
    UsersDataSource {
    override suspend fun getUsers(usersCount: Int): Result<List<User>> {
        return try {
            Result.Success(usersService.getUsers(usersCount).results)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}