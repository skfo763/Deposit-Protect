package com.skfo763.remote.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class ProductInfo(

    @Json(name = "num") val count: Int = 0,

    @Json(name = "prdSalDscnDt") val saleEndDate: String = "",

    @Json(name = "fncIstNm") val companyName: String = "",

    @Json(name = "regDate") val registerDate: String = "",

    @Json(name = "prdNm") val productName: String = ""

)