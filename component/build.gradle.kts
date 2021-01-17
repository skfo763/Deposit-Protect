import com.skfo763.gradle.lib.*

plugins {
    `android-library`
    `load-properties-lib`
    id("dagger.hilt.android.plugin")
}

android {
    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(fileTree("libs") { include("*.jar") })
    implementation(project(":util"))
    implementation(project(":base"))

    kotlinDependency()
    rxJavaDependency()
    androidCoreKtxDependency()
    daggerHiltDependency()
    androidXUiDependency()
    playCoreDependency()
    firebaseDependency()
}