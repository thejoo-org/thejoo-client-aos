package com.manjee.thejoo.data.model

import com.google.gson.annotations.SerializedName

data class Pageable(
    val sort: Sort = Sort(),
    @SerializedName("page_number")
    val pageNumber: Int = 0,
    @SerializedName("page_size")
    val pageSize: Int = 0,
    val paged: Boolean = true,
    @SerializedName("unpaged")
    val unPaged: Boolean = true,
    val offset: Int = 0
)

data class Sort(
    val sorted: Boolean = true,
    val unsorted: Boolean = true,
    val empty: Boolean = true
)