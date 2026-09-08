plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ahmed.streamgit101"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ahmed.streamgit101"
        minSdk = 24
        targetSdk = 35
        versionCode = 22
        versionName = "22.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("releaseConfig") {
            val keystorePath = System.getenv("STREAM22_KEYSTORE_PATH")
            if (!keystorePath.isNullOrBlank()) {
                storeFile = file(keystorePath)
                storePassword = System.getenv("STREAM22_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("STREAM22_KEY_ALIAS")
                keyPassword = System.getenv("STREAM22_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            val ciKeystore = System.getenv("STREAM22_KEYSTORE_PATH")
            signingConfig = if (!ciKeystore.isNullOrBlank()) {
                signingConfigs.getByName("releaseConfig")
            } else {
                signingConfigs.getByName("debug")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // CameraX
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation("androidx.camera:camera-video:1.4.1")
    implementation(libs.androidx.camera.view)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    debugImplementation(libs.androidx.ui.tooling)
}
