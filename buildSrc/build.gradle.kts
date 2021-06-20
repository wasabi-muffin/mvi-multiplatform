buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", "1.5.10"))
        classpath(kotlin("serialization", "1.5.10"))
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.0-alpha09")
    implementation(kotlin("gradle-plugin", "1.5.10"))
}

//
// kotlin {
//     // Add Deps to compilation, so it will become available in main project
//     sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
// }