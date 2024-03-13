plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ualberta.rallyup"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ualberta.rallyup"
        minSdk = 33
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Added for ourselves:

    // Firebase
    // Add the dependency for the Cloud Storage library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-storage")
    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    // FirebaseUI Storage only
    implementation ("com.firebaseui:firebase-ui-storage:7.2.0")

    // Something for Glide
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")

    // Notificaitons
    implementation("androidx.core:core-ktx:1.12.0")

    // Testings
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    androidTestImplementation("androidx.test:rules:1.4.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Our old main used below
//
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.11.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("com.google.android.gms:play-services-maps:18.2.0")
//    implementation("androidx.core:core-ktx:1.12.0")
//
//    // implementation("org.testng:testng:6.9.6")
//    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("androidx.test:rules:1.4.0")
//    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
//    implementation("com.google.zxing:core:3.4.1")
//
//    // Import the BoM for the Firebase platform
//    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))
//
//    // Add the dependency for the Cloud Storage library
//    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation("com.google.firebase:firebase-storage")
//
//    // FirebaseUI Storage only
//    implementation ("com.firebaseui:firebase-ui-storage:7.2.0")
//
//    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
//
//    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
//    implementation("com.google.firebase:firebase-analytics")
//    implementation("com.google.firebase:firebase-auth")
//    implementation("com.google.firebase:firebase-firestore")
}