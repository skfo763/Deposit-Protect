package com.skfo763.repository.data

import com.skfo763.repository.BuildConfig

fun getTestableBaseApp() = BaseAppInfo(
    "예금자보호 안심검색",
    "https://www.google.com/"
)

fun getTestableDataProvider() = DataProviderInfo(
    "예금보험공사",
    "https://www.naver.com",
    "ver.2.0"
)

data class BaseAppInfo(
    val appName: String,
    val storeUrl: String
)

data class DataProviderInfo(
    val providerName: String,
    val providerUrl: String,
    val version: String
)