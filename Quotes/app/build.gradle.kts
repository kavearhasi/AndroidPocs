plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")

    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.quotes"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quotes"
        minSdk = 28
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //sdp
    implementation(libs.sdp.android)

    // Retrofit
    implementation(libs.converter.gson)

    implementation(libs.okhttp)

    implementation(libs.retrofit)

    //Room
    implementation(libs.androidx.room.runtime)



    implementation(libs.androidx.room.ktx)

    annotationProcessor(libs.androidx.room.compiler)

    ksp(libs.androidx.room.compiler)


    //dagger hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //viewmodel coroutine
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}