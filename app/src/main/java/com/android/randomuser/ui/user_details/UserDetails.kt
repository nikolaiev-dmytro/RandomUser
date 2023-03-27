package com.android.randomuser.ui.user_details

import com.android.randomuser.data.User

data class UserDetails(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val country: String,
    val image: String
) {
    companion object {
        fun fromDataLayerUser(user: User): UserDetails {
            return UserDetails(
                user.userId,
                user.firstName,
                user.lastName,
                user.city,
                user.country,
                user.largePicture
            )
        }
    }
}
