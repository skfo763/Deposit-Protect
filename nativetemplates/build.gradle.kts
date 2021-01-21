import com.skfo763.gradle.lib.*

plugins {
    `android-library`
}

android {

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pgcfg")
            consumerProguardFile("proguard-rules.pgcfg")
        }
    }

    lintOptions {
        resourcePrefix("gnt_")
    }
}



dependencies {
    implementation(fileTree("libs") { include("*.jar") })
    implementation("androidx.appcompat:appcompat:1.1.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0")
    implementation("com.google.android.gms:play-services-ads:18.3.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
}