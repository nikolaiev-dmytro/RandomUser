package com.android.randomuser.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.randomuser.data.Gender

@Entity("user")
data class DbUser(
    @PrimaryKey val userId: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val city: String,
    val country: String,
    val phone: String,
    val smallPicture: String,
    val largePicture: String
)
