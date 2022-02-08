package com.manjee.thejoo.data.repository

interface MeRepository {
    suspend fun getUserToken(success: (String) -> Unit, fail: (Throwable) -> Unit)
}