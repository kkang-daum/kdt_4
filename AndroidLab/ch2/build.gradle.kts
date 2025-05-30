plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.ch2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ch2"
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

    //viewBinding 선언 설정..
    //이 설정만으로.. layout xml 에 해당되는 Binding 클래스가 자동 만들어진다..
    //build.gradle.kts 가 변경되면 꼭 sync now 클릭해서.. 반영해야 한다..
    viewBinding.isEnabled = true
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}