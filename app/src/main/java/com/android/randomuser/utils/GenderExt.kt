package com.android.randomuser.utils

import com.android.randomuser.data.Gender

fun Gender.string() = name.lowercase()
fun String.toGender() = when (this) {
    "male" -> Gender.MALE
    "female" -> Gender.FEMALE
    else -> Gender.UNKNOWN
}