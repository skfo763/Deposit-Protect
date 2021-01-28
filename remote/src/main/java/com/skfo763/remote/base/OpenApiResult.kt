package com.skfo763.remote.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenApiResult <T> (

    @Json(name = "header") val header: OpenApiHeader,

    @Json(name = "item") val item: List<T>,

    @Json(name = "numOfRows") val numOfRows: Int,

    @Json(name = "pageNo") val currentPageNo: Int,

    @Json(name = "totalCount") val totalCount: Int

)