// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    // the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.1" apply false

    // The dependency for the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics") version "3.0.1" apply false

    // Kotlin Symbol Processing (alternative to kapt)
    id("com.google.devtools.ksp") version "2.0.21-1.0.26" apply false
}
