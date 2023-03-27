package com.android.randomuser.ui.user_details

sealed class UserDetailsResult

object Loading : UserDetailsResult()
data class Success(val userDetails: UserDetails) : UserDetailsResult()
data class Fail(val exception: Throwable) : UserDetailsResult()
