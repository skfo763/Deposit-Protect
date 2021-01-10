package com.skfo763.gradle.lib

import com.skfo763.gradle.global.TestVersions
import com.skfo763.gradle.extension.*
import org.gradle.api.artifacts.dsl.DependencyHandler

object TestLib {
    const val jUnit = "junit:junit:${TestVersions.junit}"
    const val jUnitExt = "androidx.test.ext:junit:${TestVersions.junitExt}"
    const val testRunner = "androidx.test:runner:${TestVersions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espressoCore}"
}

fun DependencyHandler.testDependency() {
    testImplementation(TestLib.jUnit)
    testImplementation(TestLib.jUnitExt)
    androidTestImplementation(TestLib.testRunner)
    androidTestImplementation(TestLib.espresso)
}