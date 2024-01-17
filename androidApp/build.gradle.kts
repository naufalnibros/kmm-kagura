plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.naufalnibros.kagura.android"
    compileSdk = 32
    defaultConfig {
        applicationId = "com.naufalnibros.kagura.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")

    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("io.insert-koin:koin-androidx-compose:3.1.2")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
}