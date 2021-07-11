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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    implementation("com.android.tools.build:gradle:4.2.0")
    implementation(kotlin("script-runtime"))
}

//
// kotlin {
//     // Add Deps to compilation, so it will become available in main project
//     sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
// }