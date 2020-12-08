import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation(kotlin("stdlib"))

    val log4jversion = "2.14.0"
    implementation("org.apache.logging.log4j:log4j-api:$log4jversion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jversion")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}

application {
    mainClass.set("Program")
}