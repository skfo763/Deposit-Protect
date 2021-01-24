package com.skfo763.gradle.lib

import com.skfo763.gradle.global.TestVersions
import com.skfo763.gradle.extension.*
import org.gradle.api.artifacts.dsl.DependencyHandler

object TestLib {
    const val jUnit = "junit:junit:${TestVersions.junit}"
    const val jUnitExt = "androidx.test.ext:junit:${TestVersions.junitExt}"
    const val testRunner = "androidx.test:runner:${TestVersions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espressoCore}"
    const val mockito = "org.mockito:mockito-core:2.24.5"
    const val mockitoAndroid = "org.mockito:mockito-android:2.24.5"
    const val mockitoInline = "org.mockito:mockito-inline:2.13.0"
}

fun DependencyHandler.testDependency() {
    testImplementation(TestLib.jUnit)
    testImplementation(TestLib.jUnitExt)
    testImplementation(TestLib.mockito)
    androidTestImplementation(TestLib.testRunner)
    androidTestImplementation(TestLib.espresso)
    androidTestImplementation(TestLib.mockitoAndroid)
    androidTestImplementation(TestLib.mockitoInline)
}