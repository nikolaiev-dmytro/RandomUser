package com.android.randomuser.di

import android.content.Context
import androidx.room.Room
import com.android.randomuser.db.UsersDao
import com.android.randomuser.db.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideUsersDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(context, UsersDatabase::class.java, "users-db").build()
    }

    @Provides
    fun provideUsersDao(usersDatabase: UsersDatabase): UsersDao {
        return usersDatabase.usersDao()
    }
}