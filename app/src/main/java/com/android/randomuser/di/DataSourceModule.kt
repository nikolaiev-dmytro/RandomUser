package com.android.randomuser.di

import com.android.randomuser.data.UsersRepository
import com.android.randomuser.data.UsersRepositoryImpl
import com.android.randomuser.data.source.UsersDataSource
import com.android.randomuser.data.source.local.LocalUsersDataSourceImpl
import com.android.randomuser.data.source.remote.RemoteUsersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(ActivityRetainedComponent::class)
interface DataSourceModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LocalUsersDataSource

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RemoteUsersDataSource

    @Binds
    fun bindUserRepository(repositoryImpl: UsersRepositoryImpl): UsersRepository

    @Binds
    @RemoteUsersDataSource
    fun bindRemoteUsersDataSource(remoteUsersDataSource: RemoteUsersDataSourceImpl): UsersDataSource

    @Binds
    @LocalUsersDataSource
    fun bindLocalUsersDataSource(localUsersDataSource: LocalUsersDataSourceImpl): UsersDataSource
}