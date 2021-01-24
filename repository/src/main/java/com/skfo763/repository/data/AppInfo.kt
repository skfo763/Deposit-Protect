package com.skfo763.repository.data

fun getTestableBaseApp() = AppInfo(
    "com.skfo763.depositprotect",
    "https://www.google.com/",
    100000
)

fun getTestableDataProvider() = DataProvider(
    "예금보험공사",
    "https://www.naver.com",
    "ver.2.0"
)

data class AppInfo(
    val appId: String,
    val storeUrl: String,
    val versionCode: Int
)

data class DataProvider(
    val providerName: String,
    val providerUrl: String,
    val version: String
)