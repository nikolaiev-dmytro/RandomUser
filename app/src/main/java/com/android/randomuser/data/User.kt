package com.android.randomuser.data

data class User(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val city: String,
    val country: String,
    val phone: String,
    val smallPicture: String,
    val largePicture: String
)

enum class Gender {
    MALE, FEMALE, UNKNOWN
}
