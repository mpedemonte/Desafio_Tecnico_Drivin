plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.mpdev.razasperrosdrivin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mpdev.razasperrosdrivin"
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    //activity
    implementation("androidx.activity:activity-ktx:1.8.2")


    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //dagger hilt
    implementation("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:hilt-android-compiler:2.50")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // Dependencia para pruebas con MockK
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
}