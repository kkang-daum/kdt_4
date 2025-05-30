plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")//annotation 분석..
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.ch3"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ch3"
        minSdk = 24
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding.isEnabled = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //navigation component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //fragment 를 위한 다양한 확장 함수를 제공하는 라이브러리..
    implementation("androidx.fragment:fragment-ktx:1.8.7")

    //mission1 에서 등록........
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //lifecycle - process
    implementation("androidx.lifecycle:lifecycle-process:2.9.0")

    //room......
    implementation("androidx.room:room-ktx:2.7.1")
    ksp("androidx.room:room-compiler:2.7.1")
}