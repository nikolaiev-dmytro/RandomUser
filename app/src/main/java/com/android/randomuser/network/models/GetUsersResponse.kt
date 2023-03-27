package com.android.randomuser.network.models

data class GetUsersResponse(
    val results: List<User> = emptyList()
)

data class User(
    val gender: String = "",
    val name: Name = Name(),
    val login: Login = Login(),
    val location: Location = Location(),
    val phone: String = "",
    val picture: Picture = Picture()
)

data class Login(
    val uuid: String = "",
    val username: String = ""
)

data class Name(
    val title: String = "",
    val first: String = "",
    val last: String = "",
)

data class Location(
    val city: String = "",
    val country: String = "",
)

data class Picture(
    val large: String = "",
    val medium: String = "",
    val thumbnail: String = "",
)