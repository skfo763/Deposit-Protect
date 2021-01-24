package com.skfo763.remote.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppBaseInfo(
    @field:Json(name = "app_id") val appId: String,
    @field:Json(name = "store_url") val storeUrl: String,
    @field:Json(name = "version_code") val versionCode: Int
)

@JsonClass(generateAdapter = true)
data class DataProviderInfo(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "version") val version: String
)

@JsonClass(generateAdapter = true)
data class OpenSourceLicense(
    @field:Json(name = "url") val url: String
)