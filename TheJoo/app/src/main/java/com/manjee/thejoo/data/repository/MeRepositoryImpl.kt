package com.manjee.thejoo.data.repository

import android.util.Log
import com.manjee.thejoo.api.MeApi
import com.manjee.thejoo.data.model.ResponseGetUserToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MeRepositoryImpl @Inject constructor(
    private val meApi: MeApi
) : MeRepository {

    override suspend fun getUserToken(success: (String) -> Unit, fail: (Throwable) -> Unit) {
        meApi.getUserToken().enqueue(object : Callback<ResponseGetUserToken> {
            override fun onResponse(
                call: Call<ResponseGetUserToken>,
                response: Response<ResponseGetUserToken>
            ) {
                if (response.code() in 200..399) {
                    success(response.body()!!.token)
                } else {
                    Log.e(TAG, "fail getUserToken $response")
                }
            }

            override fun onFailure(call: Call<ResponseGetUserToken>, t: Throwable) {
                fail(t)
            }
        })
    }

    companion object {
        val TAG = MeRepositoryImpl::class.simpleName
    }
}