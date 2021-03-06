package com.skfo763.gradle.lib

import com.skfo763.gradle.global.Versions
import com.skfo763.gradle.extension.*
import org.gradle.kotlin.dsl.DependencyHandlerScope

object FirebaseLib {
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseBase = "com.google.android.gms:play-services-basement:${Versions.playServiceBasement}"
    const val googleAds = "com.google.android.gms:play-services-ads:${Versions.firebaseAds}"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseAds = "com.google.firebase:firebase-ads"
    const val fireStore = "com.google.firebase:firebase-firestore-ktx"
}

fun DependencyHandlerScope.firebaseDependency() {
    implementation(platform(FirebaseLib.firebaseBom))
    implementation(FirebaseLib.firebaseAds)
    implementation(FirebaseLib.googleAds)
    implementation(FirebaseLib.crashlytics)
    implementation(FirebaseLib.analytics)
    implementation(FirebaseLib.firebaseBase)
    implementation(com.skfo763.gradle.lib.FirebaseLib.fireStore)
}