package com.manjee.thejoo.data.repository

import android.util.Log
import com.manjee.thejoo.api.MeApi
import com.manjee.thejoo.data.model.ResponseGetMembershipDetail
import com.manjee.thejoo.data.model.ResponseGetUserMembership
import com.manjee.thejoo.data.model.ResponseGetUserToken
import com.manjee.thejoo.data.model.UserProfile
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

    override suspend fun getUserProfile(success: (UserProfile) -> Unit, fail: (Throwable) -> Unit) {
        meApi.getUserProfile().enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if (response.code() in 200..399) {
                    success(response.body()!!)
                } else {
                    Log.e(TAG, "fail getUserProfile $response")
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                fail(t)
            }
        })
    }

    override suspend fun getUserMembership(
        page: Int,
        success: (ResponseGetUserMembership) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        meApi.getUserMembership(page).enqueue(object : Callback<ResponseGetUserMembership> {
            override fun onResponse(
                call: Call<ResponseGetUserMembership>,
                response: Response<ResponseGetUserMembership>
            ) {
                if (response.code() in 200..399) {
                    success(response.body()!!)
                } else {
                    Log.e(TAG, "fail getUserProfile $response")
                }
            }

            override fun onFailure(call: Call<ResponseGetUserMembership>, t: Throwable) {
                fail(t)
            }
        })
    }

    override suspend fun getMembershipDetail(
        membershipId: Int,
        success: (ResponseGetMembershipDetail) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        meApi.getMembershipDetail(membershipId).enqueue(object : Callback<ResponseGetMembershipDetail> {
            override fun onResponse(
                call: Call<ResponseGetMembershipDetail>,
                response: Response<ResponseGetMembershipDetail>
            ) {
                if (response.code() in 200..399) {
                    success(response.body()!!)
                } else {
                    Log.e(TAG, "fail getUserProfile $response")
                }
            }

            override fun onFailure(call: Call<ResponseGetMembershipDetail>, t: Throwable) {
                fail(t)
            }
        })
    }

    companion object {
        val TAG = MeRepositoryImpl::class.simpleName
    }
}