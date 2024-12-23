plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // The Google services Gradle plugin
    id("com.google.gms.google-services")

    // The Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")

    // Kotlin Symbol Processing (alternative to kapt)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.kyrapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kyrapp"
        minSdk = 29
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    // Material
    implementation("com.google.android.material:material:1.5.0")

    // Import the Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")

    // Google Play services library
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    implementation("com.github.jitpack:android-example:1.0.1")

    // navigation component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")

    // Crashlytics and Analytics
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")

    // Koloda
    implementation ("com.github.Yalantis:Koloda-Android:v0.0.2-alpha")

    // Mask to an EditText
    implementation("io.github.vicmikhailau:MaskedEditText:5.0.2")

    // Retrofit and Gson
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.google.code.gson:gson:2.11.0")

    // Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp ("androidx.room:room-compiler:$room_version")
}