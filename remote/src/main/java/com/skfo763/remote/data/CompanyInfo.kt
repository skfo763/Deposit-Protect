package com.skfo763.remote.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
open class CompanyInfo(

    @field:Json(name = "num") val companyId: Int = 0,

    @field:Json(name = "fncIstNm") val name: String = ""

)

@JsonClass(generateAdapter = true)
data class BankShortcut(

    @field:Json(name = "index") val index: Int = 0,

    @field:Json(name = "bank_alias") val bankId: String = "",

    @field:Json(name = "bank_name") val shortcutBankName: String = "",

    @field:Json(name ="icon_url") val iconUrl: String = ""

)

@JsonClass(generateAdapter = true)
data class BankData(

    @field:Json(name = "index") val index: Int = 0,

    @field:Json(name = "address") val address: String = "",

    @field:Json(name = "bank_name") val bankName: String = "",

    @field:Json(name = "bank_type") val bankType: String = "",

    @field:Json(name ="telephone") val telephone: String = "",

    @field:Json(name = "url") val url: String = ""

)