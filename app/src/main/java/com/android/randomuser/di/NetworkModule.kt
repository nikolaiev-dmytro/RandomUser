package com.android.randomuser.di

import com.android.randomuser.network.RandomUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRandomUserHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val newUrl =
                    it.request().url.newBuilder().addQueryParameter("format", "pretty").build()
                val newRequest = it.request().newBuilder().url(newUrl).build()
                it.proceed(newRequest)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomUserService(okHttpClient: OkHttpClient): RandomUserService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserService::class.java)
    }
}