plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.medtronic.surgery.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.medtronic.surgery.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".staging"
            manifestPlaceholders["app_name"] = "Medtronic Digital Surgery App (Staging)"
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            // build configs for staging/debug
            buildConfigField("String", "BASE_URL", "\"https://staging.touchsurgery.com\"")
        }
        release {
            applicationIdSuffix = ""
            manifestPlaceholders["app_name"] = "Medtronic Digital Surgery App"
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // replace this when you have your own signing config
            signingConfig = signingConfigs.getByName("debug")
            // build configs for release
            // change the base url to the production url, I'll leave it to staging for now since we don't have a production url yet
            buildConfigField("String", "BASE_URL", "\"https://staging.touchsurgery.com\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.coil.compose)

    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)

    implementation(libs.di.hilt.android)
    kapt(libs.di.hilt.android.compiler)
    implementation(libs.di.hilt.navigation)
    implementation(libs.di.hilt.navigation.compose)
    implementation(libs.di.hilt.navigation.fragment)

    implementation(libs.utils.gson)
    implementation(libs.utils.retrofit.core)
    implementation(libs.utils.retrofit.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.utils.timber)

    // test libraries
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.cashapp.turbine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}