package com.android.randomuser.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.randomuser.db.DbUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM user")
    fun getUsersFlow(): Flow<List<DbUser>>

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<DbUser>

    @Insert
    suspend fun insertAll(users: List<DbUser>)
}