plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.bdapimodulo6wallet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bdapimodulo6wallet"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
//MVVM
// ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
// LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
//Retrofit
    implementation (libs.retrofit.v290)
    implementation (libs.retrofit)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
//Conversor
    implementation (libs.gson)
    implementation (libs.converter.gson)
// ROOM
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.junit.ktx)
    ksp(libs.androidx.room.compiler)
//Picasso
    implementation (libs.picasso.v271828)
//Test
    androidTestImplementation (libs.androidx.room.testing)
    implementation (libs.kotlinx.coroutines.android)
    testImplementation (libs.kotlinx.coroutines.test.v173)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.mockk)
    androidTestImplementation (libs.androidx.junit.v120)
    androidTestImplementation (libs.androidx.runner.v160)
    androidTestImplementation (libs.androidx.rules.v160)
    testImplementation (libs.robolectric)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}