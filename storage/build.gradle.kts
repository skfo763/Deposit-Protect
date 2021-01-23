import com.skfo763.gradle.extension.implementation
import com.skfo763.gradle.lib.*

plugins {
    `android-library`
    `load-properties-lib`
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":util"))
    implementation(project(":base"))

    kotlinDependency()
    rxJavaDependency()
    daggerHiltDependency()
    dataStoreDependency()
    roomDependency()
}