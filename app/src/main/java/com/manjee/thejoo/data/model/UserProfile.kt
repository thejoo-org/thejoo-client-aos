package com.manjee.thejoo.data.model

import com.google.gson.annotations.SerializedName

data class UserProfile(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = ""
)
