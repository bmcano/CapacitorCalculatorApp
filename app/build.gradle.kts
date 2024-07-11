plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = "com.brandoncano.capacitorcalculator"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.brandoncano.capacitorcalculator"
        minSdk = 24
        targetSdk = 34
        versionCode = 5
        versionName = "1.0.3"
        // reminder: update strings "about_version" and "about_last_update_date" before release
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    // androidx
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    // androidx.compose.ui
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    // androidx.compose
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.material3)
    // unit testing
    testImplementation(libs.junit)
}
