plugins {
    //project level 의 build.gradle 에 설정한 플러그인을 나도 사용하겠다.
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.androidlab"
    //컴파일러 버전.. 즉 tool 버전...
    compileSdk = 35

    defaultConfig {
        //앱의 식별자.. package 명...
        applicationId = "com.example.androidlab"
        //우리앱이 지원하는 최소버전.. 이버전까지만 install 된다..
        minSdk = 24
        //앱 개발시에 이용하는 sdk 버전.. 이 버전에서 제공되는 api 로 개발..
        targetSdk = 35
        //앱 버전..
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
}

//android sdk 에서 제공되는 api(표준 lib, platform lib) 은 바로 이용..
//platform api 가 아닌.. 수없이 많은 open source lib, 3rd lib 이용해서 개발해야 한다.
//platform api 가 아닌이상 builder 에게 알려줘야 한다.. 어떤 라이브러리 사용하겠다고..
//아래에 명시해서..
//명시만 하면 알아서 다운로드 하고, 빌드시에 알아서 참조한다..
dependencies {

    //워낙 이용비율이 높아서.. android studio 가 자동으로 추가..
    //필요 없으면 지워도 되지만.. 지우기 힘들다.. 거의 대부분 사용이 되서..
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}