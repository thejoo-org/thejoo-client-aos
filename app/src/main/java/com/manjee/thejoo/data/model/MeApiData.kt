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
