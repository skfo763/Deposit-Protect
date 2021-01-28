package com.skfo763.remote.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
open class CompanyInfo(

    @field:Json(name = "num") val companyId: Int = 0,

    @field:Json(name = "fncIstNm") val name: String = ""

)