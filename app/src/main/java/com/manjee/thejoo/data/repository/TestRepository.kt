package com.manjee.thejoo.data.repository

interface TestRepository {
    suspend fun createAuthToken(userId: Int, success: (String) -> Unit, fail: (Throwable) -> Unit)
}