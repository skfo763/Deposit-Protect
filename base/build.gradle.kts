import com.skfo763.gradle.extension.implementation
import com.skfo763.gradle.lib.*

plugins {
    `android-library`
    `load-properties-lib`
}

android {
    @Suppress("UnstableApiUsage")
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(UiLib.appCompat)

    kotlinDependency()
    navigationDependency()
    firebaseDependency()
}