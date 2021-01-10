import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.skfo763.gradle.lib.*

plugins {
    `android-library`
    `load-properties-lib`
}

dependencies {
    implementation(project(":util"))
    kotlinDependency()
    firebaseDependency()
    networkDependency()
    rxJavaDependency()
}