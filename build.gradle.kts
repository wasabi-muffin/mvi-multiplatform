// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.Gradle.sqlDelight)
        classpath(Deps.Gradle.kotlinSerialization)
        classpath(Deps.Gradle.androidGradle)
        classpath(Deps.Gradle.kotlin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}

// plugins {
//     id("org.jlleitschuh.gradle.ktlint") version Versions1.ktlint_gradle_plugin
// }

allprojects {
    version = "0.0.1"

    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    // apply(plugin = "org.jlleitschuh.gradle.ktlint")
    //
    // ktlint {
    //     version.set("0.37.2")
    //     enableExperimentalRules.set(true)
    //     verbose.set(true)
    //     filter {
    //         exclude { it.file.path.contains("build/") }
    //     }
    // }

    // afterEvaluate {
    //     tasks.named("check").configure {
    //         dependsOn(tasks.getByName("ktlintCheck"))
    //     }
    // }
}
