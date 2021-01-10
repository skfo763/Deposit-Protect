import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    defaultConfig {
        val adMobKey = gradleLocalProperties(rootDir).getProperty("admob.app.key")
        val openApiKey = gradleLocalProperties(rootDir).getProperty("open.api.key")
        val openApiUrl = gradleLocalProperties(rootDir).getProperty("open.api.url")
        manifestPlaceholders = mapOf("adMobAppKey" to adMobKey)
        buildConfigField("String", "AD_MOB_KEY", "\"$adMobKey\"")
        buildConfigField("String", "OPEN_API_KEY", "\"$openApiKey\"")
        buildConfigField("String", "OPEN_API_URL", "\"$openApiUrl\"")
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