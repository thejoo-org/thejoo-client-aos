package com.manjee.thejoo.api

import com.manjee.thejoo.data.model.ResponseGetUserMembership
import com.manjee.thejoo.data.model.ResponseGetUserToken
import com.manjee.thejoo.data.model.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MeApi {
    @GET("api/me/tokens/for-qr")
    fun getUserToken(): Call<ResponseGetUserToken>

    @GET("api/me/profile")
    fun getUserProfile(): Call<UserProfile>

    @GET("api/me/memberships")
    fun getUserMembership(
        @Query("page") page: Int,
        @Query("size") size: Int = 25
    ): Call<ResponseGetUserMembership>
}