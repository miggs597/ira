val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project

plugins {
    kotlin("jvm") version "1.8.21"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}

group = "com.worldmarket"
version = "0.0.1"
application {
    mainClass.set("com.worldmarket.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

val osName = System.getProperty("os.name")
val targetOs = when {
    osName == "Mac OS X" -> "macos"
    osName.startsWith("Win") -> "windows"
    osName.startsWith("Linux") -> "linux"
    else -> error("Unsupported OS: $osName")
}

val osArch = System.getProperty("os.arch")
var targetArch = when (osArch) {
    "x86_64", "amd64" -> "x64"
    "aarch64" -> "arm64"
    else -> error("Unsupported arch: $osArch")
}

val target = "${targetOs}-${targetArch}"

dependencies {
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("com.microsoft.sqlserver:mssql-jdbc:12.2.0.jre11")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.google.zxing:core:3.5.1")
    implementation("com.google.zxing:javase:3.5.1")
    implementation("io.ktor:ktor-server-core-jvm:2.3.1")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.1")
    implementation("io.ktor:ktor-server-status-pages:2.3.1")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.1")
    implementation("io.ktor:ktor-server-host-common-jvm:2.3.1")
    implementation("io.ktor:ktor-server-status-pages-jvm:2.3.1")
    implementation("org.jetbrains.skiko:skiko-awt-runtime-$target:0.7.58")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.1")
}

kotlin {
    jvmToolchain(11)
}