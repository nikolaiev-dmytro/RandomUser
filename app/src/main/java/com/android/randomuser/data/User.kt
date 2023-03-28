package com.android.randomuser.data

import com.android.randomuser.db.DbUser
import com.android.randomuser.utils.toGender

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
) {

    fun toDbUser(): DbUser {
        return DbUser(
            userId,
            firstName,
            lastName,
            gender,
            city,
            country,
            phone,
            smallPicture,
            largePicture
        )
    }

    companion object {

        fun fromDbUser(user: DbUser): User {
            return User(
                user.userId,
                user.firstName,
                user.lastName,
                user.gender,
                user.city,
                user.country,
                user.phone,
                user.smallPicture,
                user.largePicture
            )
        }

        fun fromNetworkUser(user: com.android.randomuser.network.models.User): User {
            return User(
                user.login.uuid,
                user.name.first,
                user.name.last,
                user.gender.toGender(),
                user.location.city,
                user.location.country,
                user.phone,
                user.picture.thumbnail,
                user.picture.large
            )
        }
    }
}

enum class Gender {
    MALE, FEMALE, UNKNOWN
}
