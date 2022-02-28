import com.patrykkosieradzki.cryptobag.buildsrc.Libs
import com.patrykkosieradzki.cryptobag.buildsrc.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion= Versions.compose
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(project(":utils-android"))

    // Dependency injection
    implementation("com.google.dagger:hilt-android:2.37")
    kapt("com.google.dagger:hilt-android-compiler:2.37")

    implementation(Libs.AndroidX.Compose.ui)
    implementation("androidx.compose.ui:ui-tooling:1.1.0")
    implementation("androidx.compose.foundation:foundation:1.1.0")
    implementation(Libs.AndroidX.Compose.compiler)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.AndroidX.Lifecycle.lifeCycleRuntimeKtx)

    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("io.coil-kt:coil-svg:1.4.0")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}