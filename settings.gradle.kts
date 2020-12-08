pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    plugins {
        kotlin("jvm") version "1.4.21-346"
        kotlin("plugin.serialization") version "1.4.21-346"
        id("org.jetbrains.compose") version "0.2.0-build132"
    }
}

rootProject.name = "compose-serialization-issue"

include(":with-compose", ":without-compose")
