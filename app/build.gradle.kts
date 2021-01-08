import com.skfo763.gradle.lib.*

plugins {
    `android-application`
    `load-properties-app`
    `signing-config`
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
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