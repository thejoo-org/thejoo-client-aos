package com.manjee.thejoo.data.model

import com.google.gson.annotations.SerializedName

data class ResponseGetUserToken(
    val token: String = ""
)

data class ResponseGetUserMembership(
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_elements")
    val totalElements: Int = 0,
    val sort: Sort = Sort(),
    val first: Boolean = true,
    val last: Boolean = true,
    val number: Int = 0,
    @SerializedName("number_of_elements")
    val numberOfElements: Int = 0,
    val pageable: Pageable = Pageable(),
    val size: Int = 0,
    val content: ArrayList<UserMembership> = arrayListOf(),
    val empty: Boolean = true
)

data class ResponseGetMembershipDetail(
    val id: Int = 0,
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("store_id")
    val storeId: Int = 0,
    val store: Store = Store(),
    val point: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("latest_apply_transaction_history")
    val latestApplyTransactionHistory: Transaction = Transaction()
)
