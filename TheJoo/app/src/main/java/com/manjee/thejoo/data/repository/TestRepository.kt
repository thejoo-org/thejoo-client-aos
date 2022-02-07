package com.manjee.thejoo.data.repository

interface TestRepository {
    suspend fun createUserToken(userId: Int, success: (String) -> Unit, fail: (Throwable) -> Unit)
}