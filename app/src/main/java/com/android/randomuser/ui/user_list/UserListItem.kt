package com.android.randomuser.ui.user_list

import com.android.randomuser.data.Gender
import com.android.randomuser.data.User

data class UserListItem(
    val userId: String,
    val fullName: String,
    val location: String,
    val gender: Gender,
    val image: String
) {
    companion object {
        fun fromDataLayerUser(user: User): UserListItem {
            return UserListItem(
                user.userId,
                StringBuilder(user.firstName).append(" ").append(user.lastName).toString(),
                StringBuilder(user.city).append(", ").append(user.country).toString(),
                user.gender,
                user.smallPicture
            )
        }
    }
}
