package com.manjee.thejoo.data.model

import com.google.gson.annotations.SerializedName

data class UserMembership(
    val id: Int = 0,
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("store_id")
    val storeId: Int = 0,
    @SerializedName("store_name")
    val storeName: String = "",
    val point: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = ""
)
