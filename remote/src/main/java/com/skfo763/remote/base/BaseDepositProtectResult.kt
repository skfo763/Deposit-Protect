package com.skfo763.remote.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyApiResult <T> (
    @Json(name = "getCompanyList") val result: OpenApiResult<T>
)

@JsonClass(generateAdapter = true)
data class ProductApiResult <T> (
    @Json(name = "getProductList") val result: OpenApiResult<T>
)
