import com.skfo763.gradle.lib.*

plugins {
    `android-application`
    `load-properties-app`
    `signing-config`
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-android")
}

android {
    defaultConfig {
        applicationId = com.skfo763.gradle.global.AppVersion.applicationId
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    bundle {
        language {
            enableSplit = false
        }
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }

    buildTypes {
        getByName("debug") {
            firebaseCrashlytics {
                mappingFileUploadEnabled = false
            }
        }
    }
}

dependencies {
    implementation(project(":util"))
    implementation(project(":base"))
    implementation(project(":component"))
    implementation(project(":remote"))
    implementation(project(":nativetemplates"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    kotlinDependency()
    rxJavaDependency()
    androidCoreKtxDependency()
    daggerHiltDependency()
    lifecycleDependency()
    androidXUiDependency()
    navigationDependency()
    firebaseDependency()
    networkDependency()
    roomDependency()
}