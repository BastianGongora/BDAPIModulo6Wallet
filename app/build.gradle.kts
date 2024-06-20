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
    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v282)

    // LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx.v282)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.logging.interceptor)

    // Conversor
    implementation (libs.converter.gson)

    // ROOM
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    implementation(libs.core.ktx)
    implementation(libs.androidx.junit.ktx)
    ksp (libs.androidx.room.compiler)

    // Navigation
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    // Picasso
    implementation (libs.picasso.v271828)

    // Testing
    testImplementation (libs.mockito.core.v530)
    testImplementation (libs.kotlinx.coroutines.test.v173)
    testImplementation (libs.androidx.core.testing)

    androidTestImplementation (libs.androidx.rules.v150)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core.v340)
    androidTestImplementation (libs.androidx.runner.v110)
    androidTestUtil (libs.androidx.orchestrator)
    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)
    implementation (libs.material)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.constraintlayout)

    testImplementation (libs.junit)
}