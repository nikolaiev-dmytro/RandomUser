package com.android.randomuser.data.source.remote

import com.android.randomuser.data.User
import com.android.randomuser.data.source.UsersDataSource
import com.android.randomuser.network.RandomUserService
import com.android.randomuser.utils.ApiServerError
import com.android.randomuser.utils.InternetConnectionError
import com.android.randomuser.utils.toGender
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class RemoteUsersDataSourceImpl @Inject constructor(private val usersService: RandomUserService) :
    UsersDataSource {
    override suspend fun fetchUsers(userCount: Int): Result<List<User>> {
        return try {
            Result.success(usersService.getUsers(userCount).results.map { User.fromNetworkUser(it) })
        } catch (e: Throwable) {
            when (e) {
                is HttpException -> {
                    Result.failure(ApiServerError(e.getErrorMessage()))
                }
                is ConnectException -> {
                    Result.failure(InternetConnectionError())
                }
                is UnknownHostException -> {
                    Result.failure(InternetConnectionError())
                }
                else -> {
                    Result.failure(e)
                }
            }
        }
    }

    override fun observeHistory(): Flow<List<User>> {
        return emptyFlow()
    }

    override suspend fun insertUsers(users: List<User>) = Unit

}

fun HttpException.getErrorMessage(): String? {
    return try {
        JSONObject(this.response()?.errorBody()?.string() ?: "")
            .optString("error", "")
    } catch (e: Throwable) {
        null
    }
}