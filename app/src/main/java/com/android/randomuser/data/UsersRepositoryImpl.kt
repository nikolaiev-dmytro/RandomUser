package com.android.randomuser.data

import com.android.randomuser.data.source.UsersDataSource
import com.android.randomuser.di.DataSourceModule
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class UsersRepositoryImpl @Inject constructor(
    @DataSourceModule.RemoteUsersDataSource private val remoteUsersDataSource: UsersDataSource,
    @DataSourceModule.LocalUsersDataSource private val localUsersDataSource: UsersDataSource
) : UsersRepository {
    private val users: MutableStateFlow<Result<List<User>>> =
        MutableStateFlow(Result.success(emptyList()))

    override suspend fun fetchUsers(usersCount: Int) {
        users.emit(remoteUsersDataSource.fetchUsers(usersCount))
    }

    override fun observeUsers(): Flow<Result<List<User>>> {
        return users
    }

    override fun observeHistory(): Flow<List<User>> {
        return localUsersDataSource.observeHistory()
    }

}