package com.manjee.thejoo.data.repository

import android.util.Log
import com.manjee.thejoo.api.TestApi
import com.manjee.thejoo.data.model.ResponseCreateUserToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testApi: TestApi
) : TestRepository {

    override suspend fun createUserToken(
        userId: Int,
        success: (String) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        testApi.createUserToken(userId).enqueue(object : Callback<ResponseCreateUserToken> {
            override fun onResponse(
                call: Call<ResponseCreateUserToken>,
                response: Response<ResponseCreateUserToken>
            ) {
                if (response.code() in 200..399) {
                    success(response.body()!!.token)
                } else {
                    Log.e(TAG, "fail createUserToken $response")
                }
            }

            override fun onFailure(call: Call<ResponseCreateUserToken>, t: Throwable) {
                fail(t)
            }
        })
    }

    companion object {
        val TAG = TestRepositoryImpl::class.simpleName
    }
}