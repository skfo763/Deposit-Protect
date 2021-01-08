package com.skfo763.gradle.lib

import com.skfo763.gradle.global.Versions
import com.skfo763.gradle.extension.*
import org.gradle.api.artifacts.dsl.DependencyHandler

object DataStoreLib {
    const val prefDataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    const val protoDataStore = "androidx.datastore:datastore-core:${Versions.dataStore}"
}

object RoomLib {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomDebug = "com.amitshekhar.android:debug-db:${Versions.roomDebug}"
}

fun DependencyHandler.dataStoreDependency() {
    implementation(DataStoreLib.prefDataStore)
    implementation(DataStoreLib.protoDataStore)
}

fun DependencyHandler.roomDependency() {
    implementation(RoomLib.roomRuntime)
    implementation(RoomLib.roomKtx)
    debugImplementation(RoomLib.roomDebug)
    kapt(RoomLib.roomCompiler)
}