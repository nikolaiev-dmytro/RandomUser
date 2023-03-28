package com.android.randomuser.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.android.randomuser.data.Gender
import com.android.randomuser.db.DbUser
import com.android.randomuser.db.UsersDao
import com.android.randomuser.utils.string
import com.android.randomuser.utils.toGender
import javax.inject.Singleton

@Database(entities = [DbUser::class], version = 1)
@TypeConverters(Converters::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}

class Converters {
    @TypeConverter
    fun toGender(genderString: String) = genderString.toGender()

    @TypeConverter
    fun fromGender(gender: Gender) = gender.string()
}