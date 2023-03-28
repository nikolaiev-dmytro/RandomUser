package com.android.randomuser.network

import com.android.randomuser.network.models.GetUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("/api")
    suspend fun getUsers(@Query("results") userCount: Int): GetUsersResponse
}