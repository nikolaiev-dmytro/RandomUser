package com.android.randomuser.data.source.remote

import com.android.randomuser.data.User
import com.android.randomuser.data.source.UsersDataSource
import com.android.randomuser.network.RandomUserService
import com.android.randomuser.utils.toGender
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class RemoteUsersDataSourceImpl @Inject constructor(private val usersService: RandomUserService) :
    UsersDataSource {
    override suspend fun fetchUsers(userCount: Int): Result<List<User>> {
        return try {
            Result.success(usersService.getUsers(userCount).results.map {
                User(
                    it.login.uuid,
                    it.name.first,
                    it.name.last,
                    it.gender.toGender(),
                    it.location.city,
                    it.location.country,
                    it.phone,
                    it.picture.thumbnail,
                    it.picture.large
                )
            })

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override fun observeHistory(): Flow<List<User>> {
        return emptyFlow()
    }


}