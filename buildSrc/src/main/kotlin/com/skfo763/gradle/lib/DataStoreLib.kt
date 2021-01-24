package com.skfo763.gradle.lib

import com.skfo763.gradle.global.Versions
import com.skfo763.gradle.extension.*
import org.gradle.api.artifacts.dsl.DependencyHandler

object DataStoreLib {
    const val prefDataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    const val rxDataStoreWrapper = "androidx.datastore:datastore-preferences-rxjava3:${Versions.dataStore}"
}

object RoomLib {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomDebug = "com.amitshekhar.android:debug-db:${Versions.roomDebug}"
}

fun DependencyHandler.dataStoreDependency() {
    implementation(DataStoreLib.prefDataStore)
    implementation(com.skfo763.gradle.lib.DataStoreLib.rxDataStoreWrapper)
}

fun DependencyHandler.roomDependency() {
    implementation(RoomLib.roomRuntime)
    implementation(RoomLib.roomKtx)
    debugImplementation(RoomLib.roomDebug)
    kapt(RoomLib.roomCompiler)
}