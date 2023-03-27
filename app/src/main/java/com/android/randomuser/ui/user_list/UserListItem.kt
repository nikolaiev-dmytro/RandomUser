package com.android.randomuser.ui.user_list

import com.android.randomuser.data.Gender

data class UserListItem(
    val userId: String,
    val fullName: String,
    val location: String,
    val gender: Gender,
    val image: String
)
