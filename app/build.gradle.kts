plugins {
    autowire(libs.plugins.android.application)
    autowire(libs.plugins.kotlin.android)
}

android {
    namespace = "fury.typerx"
    compileSdk = 34

    defaultConfig {
        applicationId = "fury.typerx"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions"
        )
    }
    composeOptions {
        kotlinCompilerExtensionVersion = property.project.android.compose.kotlinCompilerExtensionVersion
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(com.highcapable.flexiui.core.android)
    implementation(com.highcapable.betterandroid.ui.component)
    implementation(com.highcapable.betterandroid.ui.extension)
    implementation(com.highcapable.betterandroid.system.extension)
    implementation(com.highcapable.betterandroid.compose.extension)
    implementation(androidx.activity.activity)
    implementation(androidx.activity.activity.compose)
    implementation(platform(androidx.compose.compose.bom))
    implementation(androidx.compose.ui.ui)
    implementation(androidx.compose.ui.ui.graphics)
    implementation(androidx.compose.ui.ui.tooling)
    implementation(androidx.compose.ui.ui.tooling.preview)
    implementation(androidx.compose.foundation.foundation)
    implementation(androidx.navigation.navigation.compose)
    testImplementation(junit.junit)
    androidTestImplementation(androidx.test.ext.junit)
    androidTestImplementation(androidx.test.espresso.espresso.core)
}