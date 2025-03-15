plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.logins"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.logins"
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
    implementation(libs.androidx.annotation)
    implementation(libs.support.annotations)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // sdp
    implementation (libs.sdp.android)

    //Co routine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    //Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //ViewModelScope
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    //Retrofit
    implementation (libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.retrofit)

    //Pagination
    implementation (libs.androidx.paging.runtime)
    implementation (libs.androidx.paging.rxjava2)
    implementation (libs.androidx.paging.guava)

    //DaggerHilt
    implementation (libs.hilt.android)
   // implementation (libs.androidx.hilt.lifecycle.viewmodel)
    implementation (libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)
    ksp (libs.androidx.hilt.compiler)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)


    //splash screen
    implementation (libs.androidx.core.splashscreen)

    //Datastore
    //implementation ("androidx.datastore:datastore-preferences:1.1.2")
    implementation (libs.androidx.datastore.preferences)
    //implementation "androidx.datastore:datastore-preferences-core:1.1.2"
    implementation (libs.androidx.datastore.preferences.core)


}