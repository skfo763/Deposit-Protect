package com.skfo763.remote.base

import com.google.firebase.firestore.QuerySnapshot
import com.squareup.moshi.Moshi

fun <T> Map<String, Any>?.toCustomDataType(moshi: Moshi, clazz: Class<T>, dataOnNull: T): T {
    return moshi.adapter(clazz).fromJsonValue(this) ?: dataOnNull
}

fun <T> Map<String, Any>?.toCustomDataType(moshi: Moshi, clazz: Class<T>): T? {
    return moshi.adapter(clazz).fromJsonValue(this)
}

fun <T> QuerySnapshot?.toCustomListDataType(moshi: Moshi, clazz: Class<T>, dataOnNull: T): List<T> {
    return this?.documents?.map {
        it.data.toCustomDataType(moshi, clazz) ?: dataOnNull
    } ?: listOf()
}

fun <T> QuerySnapshot?.toCustomListDataType(moshi: Moshi, clazz: Class<T>): List<T?> {
    return this?.documents?.map {
        it.data.toCustomDataType(moshi, clazz)
    } ?: listOf()
}