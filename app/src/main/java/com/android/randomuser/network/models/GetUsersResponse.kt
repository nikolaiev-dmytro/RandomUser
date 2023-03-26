package com.android.randomuser.network.models

data class GetUsersResponse(
    val results: List<User> = emptyList()
)

data class User(
    val gender: String = "",
    val name: Name = Name(),
    val login: Login = Login(),
    val location: Location = Location(),
    val dob: DateOfBirth = DateOfBirth(),
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

data class Street(
    val number: Int = 0,
    val name: String = ""
)

data class Coordinates(
    val latitude: String = "",
    val longitude: String = ""
)

data class TimeZone(
    val offset: String = "",
    val description: String = ""
)

data class Location(
    val city: String = "",
    val state: String = "",
    val country: String = "",
    val postcode: Int = 0,
    val street: Street = Street(),
    val coordinates: Coordinates = Coordinates(),
    val timeZone: TimeZone = TimeZone(),
)

data class DateOfBirth(
    val date: String = "",
    val age: Int = 0
)

data class Picture(
    val large: String = "",
    val medium: String = "",
    val thumbnail: String = "",
)