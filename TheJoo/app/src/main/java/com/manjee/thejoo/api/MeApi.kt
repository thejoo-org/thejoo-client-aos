package com.manjee.thejoo.api

import com.manjee.thejoo.data.model.ResponseGetUserToken
import retrofit2.Call
import retrofit2.http.GET

interface MeApi {

    @GET("api/me/tokens/for-qr")
    fun getUserToken(): Call<ResponseGetUserToken>
}