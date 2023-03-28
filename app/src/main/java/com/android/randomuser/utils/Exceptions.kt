package com.android.randomuser.utils

class InternetConnectionError : Exception("It seems you have internet connection issue")
class ApiServerError(errorMessage: String?) : Exception(errorMessage ?: "Unknown server error")
class NoUserFoundError : Exception("User not found")