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
    implementation(project(":remote"))
    implementation(project(":storage"))

    kotlinDependency()
    rxJavaDependency()
    daggerHiltDependency()
    pagingDependency()
}