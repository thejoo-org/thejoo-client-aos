package com.manjee.thejoo.data.repository

import com.manjee.thejoo.data.model.ResponseGetUserMembership
import com.manjee.thejoo.data.model.UserProfile

interface MeRepository {
    suspend fun getUserToken(success: (String) -> Unit, fail: (Throwable) -> Unit)
    suspend fun getUserProfile(success: (UserProfile) -> Unit, fail: (Throwable) -> Unit)
    suspend fun getUserMembership(
        page: Int,
        success: (ResponseGetUserMembership) -> Unit,
        fail: (Throwable) -> Unit
    )
}