plugins {
    alias(libs.plugins.androidApplication)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "edu.tacoma.uw.csscompass"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.tacoma.uw.csscompass"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures{
        viewBinding= true
    }
}

dependencies {
//    implementation("rome:rome:1.0")                     // This allow us to use the ROME framework
    implementation("com.android.volley:volley:1.2.1")   //Allow to use GET and POST for http requests
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}