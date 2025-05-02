plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    //우리 프로젝트에서 필요한 라이브러리 등록..
    //json 은 일반적으로는 문자열이다.. 이 문자열을 해석해서 우리가 원하는 데이터를
    //추출한다.. 이를 파싱이라고 하는데.. 이 json 파싱 라이브러리가 많다..
    implementation("com.google.code.gson:gson:2.8.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}