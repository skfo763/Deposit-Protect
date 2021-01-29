import java.io.FileInputStream
import java.util.*
import com.skfo763.gradle.global.KeyStore

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    signingConfigs {
        create("release") {
            val keyStoreProperty = Properties()
            try {
                keyStoreProperty.load(FileInputStream(rootProject.file(KeyStore.releaseKeyStoreFile)))
                keyAlias = keyStoreProperty.getProperty(KeyStore.keyAlias)
                keyPassword = keyStoreProperty.getProperty(KeyStore.keyPassword)
                storeFile = file(keyStoreProperty.getProperty(KeyStore.storeFile))
                storePassword = keyStoreProperty.getProperty(KeyStore.storePassword)
            } catch (e: Exception) {
                keyAlias = ""
                keyPassword = ""
                storePassword = ""
            }
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
        }
    }
}