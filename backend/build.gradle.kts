plugins {
    kotlin("jvm") version "1.9.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("io.ktor:ktor-server-cors:2.3.4")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-tests:2.3.4")
    testImplementation("io.ktor:ktor-client-content-negotiation:2.3.4")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
}

application {
    mainClass.set("com.example.backend.ApplicationKt")
}

kotlin {
    jvmToolchain(17)
}