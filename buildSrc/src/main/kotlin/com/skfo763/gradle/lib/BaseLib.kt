package com.skfo763.gradle.lib

import com.skfo763.gradle.global.Versions
import com.skfo763.gradle.extension.*
import org.gradle.api.artifacts.dsl.DependencyHandler

object AndroidCoreKtxLib {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
}

object RxJavaLib {
    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBinding}"
    const val rxBindingCore = "com.jakewharton.rxbinding4:rxbinding-core:${Versions.rxBinding}"
    const val autoDisposable = "com.uber.autodispose2:autodispose:${Versions.autoDisposable}"
    const val autoDisposableLifecycle = "com.uber.autodispose2:autodispose-lifecycle:${Versions.autoDisposable}"
    const val autoDisposableAndroid = "com.uber.autodispose2:autodispose-android:${Versions.autoDisposable}"
    const val autoDisposableAndLife = "com.uber.autodispose2:autodispose-androidx-lifecycle:${Versions.autoDisposable}"
}

object KotlinLib {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val coroutineExt = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1"
}

object DaggerHiltLib {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltApp}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltApp}"
    const val hiltCommon = "androidx.hilt:hilt-common:${Versions.hiltApp}"
}

object PagingLib {
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val pagingRx = "androidx.paging:paging-rxjava3:${Versions.paging}"
}

fun DependencyHandler.androidCoreKtxDependency() {
    implementation(AndroidCoreKtxLib.coreKtx)
    implementation(AndroidCoreKtxLib.activityKtx)
    implementation(AndroidCoreKtxLib.fragmentKtx)
}

fun DependencyHandler.kotlinDependency() {
    implementation(KotlinLib.kotlin)
    implementation(KotlinLib.coroutine)
    implementation(KotlinLib.coroutineExt)
}

fun DependencyHandler.rxJavaDependency() {
    implementation(RxJavaLib.rxJava)
    implementation(RxJavaLib.rxAndroid)
    implementation(RxJavaLib.rxBinding)
    implementation(RxJavaLib.rxBindingCore)
    implementation(RxJavaLib.autoDisposable)
    implementation(RxJavaLib.autoDisposableLifecycle)
    implementation(RxJavaLib.autoDisposableAndroid)
    implementation(RxJavaLib.autoDisposableAndLife)
}

fun DependencyHandler.daggerHiltDependency() {
    kapt(DaggerHiltLib.hiltCompiler)
    kapt(DaggerHiltLib.daggerHiltCompiler)
    implementation(DaggerHiltLib.hilt)
    implementation(DaggerHiltLib.hiltViewModel)
    implementation(DaggerHiltLib.hiltCommon)
}

fun DependencyHandler.pagingDependency() {
    implementation(com.skfo763.gradle.lib.PagingLib.paging)
    implementation(com.skfo763.gradle.lib.PagingLib.pagingRx)
}