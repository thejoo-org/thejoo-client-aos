package com.manjee.thejoo.api

import com.manjee.thejoo.data.model.ResponseCreateUserToken
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TestApi {

    @GET("api/test/tokens/{userId}")
    fun createUserToken(
        @Path(value = "userId") userId: Int
    ): Call<ResponseCreateUserToken>
}