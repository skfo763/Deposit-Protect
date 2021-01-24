package com.skfo763.remote.base

import com.squareup.moshi.Moshi

fun <T> Map<String, Any>?.toCustomDataType(moshi: Moshi, clazz: Class<T>, dataOnNull: T): T {
    return moshi.adapter(clazz).fromJsonValue(this) ?: dataOnNull
}

fun <T> Map<String, Any>?.toCustomDataType(moshi: Moshi, clazz: Class<T>): T? {
    return moshi.adapter(clazz).fromJsonValue(this)
}