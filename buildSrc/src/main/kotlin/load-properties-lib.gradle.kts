import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    defaultConfig {
        val adMobKey = gradleLocalProperties(rootDir).getProperty("admob.app.key")
        buildConfigField("String", "AD_MOB_KEY", "\"$adMobKey\"")
    }

    buildTypes {
        getByName("release") {
            val fullAdUnitKey = gradleLocalProperties(rootDir).getProperty("admob.ads.unit.full.release")
            buildConfigField("String", "AD_MOB_UNIT_FULL", "\"$fullAdUnitKey\"")
        }

        getByName("debug") {
            val fullAdUnitKey = gradleLocalProperties(rootDir).getProperty("admob.ads.unit.full.debug")
            buildConfigField("String", "AD_MOB_UNIT_FULL", "\"$fullAdUnitKey\"")
        }
    }
}