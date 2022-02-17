package com.manjee.thejoo.data.model

import com.google.gson.annotations.SerializedName

data class Store(
    val id: Int = 0,
    @SerializedName("owner_id")
    val ownerId: Int = 0,
    val name: String = "",
    val email: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = ""
)

data class Transaction(
    val id: Int = 0,
    val type: String = "",
    val status: String = "",
    @SerializedName("promotion_id")
    val promotionId: Int = 0,
    @SerializedName("added_point")
    val addedPoint: Int = 0,
    @SerializedName("point_snapshot")
    val pointSnapshot: Int = 0,
    @SerializedName("store_id")
    val storeId: Int = 0,
    @SerializedName("membership_id")
    val membershipId: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = ""
)
