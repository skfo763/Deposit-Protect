package com.skfo763.remote.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenApiHeader (

    @Json(name = "resultCode") val resultCode: String,

    @Json(name = "resultMsg") val resultMsg: String

)
