plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.newzapp"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.example.newzapp"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    // This replaces the deprecated kotlinOptions
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}

dependencies {

    implementation(libs.androidx.compose.animation)
    val room_version = "2.6.1"   // recommended stable

    // Room runtime
    implementation("androidx.room:room-runtime:$room_version")

    // Kotlin + Coroutines support
    implementation("androidx.room:room-ktx:$room_version")

    implementation("com.exyte:animated-navigation-bar:1.0.0")
    // Room compiler (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    implementation(
        "com.squareup.retrofit2:retrofit:2.9.0"
    )

    implementation(
        "com.squareup.retrofit2:converter-gson:2.9.0"
    )

    // OkHttp
    implementation(
        "com.squareup.okhttp3:okhttp:4.12.0"
    )
    // WorkManager
    implementation(
        "androidx.work:work-runtime-ktx:2.9.0"
    )


    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil:2.5.0")
    // Splash Screen
    implementation(
        "androidx.core:core-splashscreen:1.0.1"
    )

    // Material
    implementation(
        "com.google.android.material:material:1.11.0"
    )
    implementation("androidx.compose.material:material-icons-extended:1.7.6")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation("androidx.compose.material3:material3:1.3.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation("androidx.navigation:navigation-compose:2.8.5")



}
ksp{
    arg("room.schemaLocation","$projectDir/schemas")
}