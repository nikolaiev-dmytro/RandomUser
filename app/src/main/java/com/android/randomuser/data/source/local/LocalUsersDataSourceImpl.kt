package com.android.randomuser.data.source.local

import com.android.randomuser.data.User
import com.android.randomuser.data.source.UsersDataSource
import com.android.randomuser.db.UsersDao
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class LocalUsersDataSourceImpl @Inject constructor(
    private val usersDao: UsersDao
) : UsersDataSource {
    private val localUsers = usersDao.getUsers()
    override suspend fun fetchUsers(userCount: Int): Result<List<User>> {
        return Result.success(emptyList())
    }

    override fun observeHistory(): Flow<List<User>> {
        return localUsers.map {
            it.map { user ->
                User(
                    user.userId,
                    user.firstName,
                    user.lastName,
                    user.gender,
                    user.city,
                    user.country,
                    user.phone,
                    user.smallPicture,
                    user.largePicture
                )
            }
        }
    }
}