package com.android.randomuser.data

import com.android.randomuser.data.source.UsersDataSource
import com.android.randomuser.di.DataSourceModule
import com.android.randomuser.utils.NoUserFoundError
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ActivityRetainedScoped
class UsersRepositoryImpl @Inject constructor(
    @DataSourceModule.RemoteUsersDataSource private val remoteUsersDataSource: UsersDataSource,
    @DataSourceModule.LocalUsersDataSource private val localUsersDataSource: UsersDataSource
) : UsersRepository {


    override suspend fun fetchUsers(usersCount: Int): Result<List<User>> {
        val result = remoteUsersDataSource.fetchUsers(usersCount)
        if (result.isSuccess) {
            result.getOrNull()?.let { localUsersDataSource.insertUsers(it) }
        }
        return result
    }

    override fun observeHistory(): Flow<List<User>> {
        return localUsersDataSource.observeHistory()
    }

    override suspend fun getUser(userId: String): Result<User> {
        val predicate: (User) -> Boolean = { it.userId == userId }
        val user = (localUsersDataSource.getUsers().find(predicate)
            ?: remoteUsersDataSource.getUsers().find(predicate))
        return user?.let { Result.success(it) } ?: Result.failure(NoUserFoundError())
    }
}